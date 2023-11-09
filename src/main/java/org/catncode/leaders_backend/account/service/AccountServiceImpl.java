package org.catncode.leaders_backend.account.service;

import org.catncode.leaders_backend.account.dto.AccountRole;
import org.catncode.leaders_backend.account.dto.CreateAccountDto;
import org.catncode.leaders_backend.account.dto.UpdateAccountDto;
import org.catncode.leaders_backend.account.dto.UpdateAccountPasswordDto;
import org.catncode.leaders_backend.account.entity.Account;
import org.catncode.leaders_backend.account.exception.AccountNotFoundException;
import org.catncode.leaders_backend.account.repository.AccountRepository;
import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.employee.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public AccountServiceImpl(
            AccountRepository accountRepository,
            EmployeeRepository employeeRepository,
            PasswordEncoder passwordEncoder,
            ModelMapper modelMapper
    ) {
        this.accountRepository = accountRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public Account create(CreateAccountDto dto) throws AppException {
        var account = modelMapper.map(dto, Account.class);
        account.setPasswordHash(
                passwordEncoder.encode(dto.getPassword())
        );

        return accountRepository.save(account);
    }

    @Override
    public Page<Account> getAll(Pageable pageable) throws AppException {
        return accountRepository.findAll(pageable);
    }

    @Override
    public Account getById(Integer id) throws AppException {
        return accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
    }

    @Override
    public Account getByLogin(String login) throws AppException {
        return accountRepository.findByLogin(login).orElseThrow(AccountNotFoundException::new);
    }

    @Override
    public Account updateById(Integer id, UpdateAccountDto dto) throws AppException {
        var account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
        if (account.getRole() == AccountRole.EMPLOYEE && dto.getRole() != AccountRole.EMPLOYEE) {
            employeeRepository.delete(account.getEmployee());
            account.setEmployee(null);
        }

        modelMapper.map(dto, account);
        return accountRepository.save(account);
    }

    @Override
    public Account updateByLogin(String login, UpdateAccountDto dto) throws AppException {
        var account = accountRepository.findByLogin(login).orElseThrow(AccountNotFoundException::new);
        if (account.getRole() == AccountRole.EMPLOYEE && dto.getRole() != AccountRole.EMPLOYEE) {
            employeeRepository.delete(account.getEmployee());
            account.setEmployee(null);
        }

        modelMapper.map(dto, account);
        return accountRepository.save(account);
    }

    @Override
    public Account updatePasswordById(Integer id, UpdateAccountPasswordDto dto) throws AppException {
        var account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
        account.setPasswordHash(
                passwordEncoder.encode(dto.getNewPassword())
        );

        return accountRepository.save(account);
    }

    @Override
    public void deleteById(Integer id) throws AppException {
        if (!accountRepository.existsById(id)){
            throw new AccountNotFoundException();
        }
        accountRepository.deleteById(id);
    }

    @Override
    public void deleteByLogin(String login) throws AppException {
        var account = accountRepository.findByLogin(login).orElseThrow(AccountNotFoundException::new);
        accountRepository.delete(account);
    }
}
