package org.catncode.leaders_backend.account.controller;

import org.catncode.leaders_backend.account.dto.AccountDto;
import org.catncode.leaders_backend.account.dto.CreateAccountDto;
import org.catncode.leaders_backend.account.dto.UpdateAccountDto;
import org.catncode.leaders_backend.account.dto.UpdateAccountPasswordDto;
import org.catncode.leaders_backend.account.service.AccountService;
import org.catncode.leaders_backend.core.dto.Pagination;
import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.core.factory.PaginationFactory;
import org.catncode.leaders_backend.security.dto.AccountDetails;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountControllerImpl implements AccountController{

    private final AccountService accountService;
    private final ModelMapper modelMapper;
    private final PaginationFactory paginationFactory;

    public AccountControllerImpl(
            AccountService accountService,
            ModelMapper modelMapper,
            PaginationFactory paginationFactory
    ) {
        this.accountService = accountService;
        this.modelMapper = modelMapper;
        this.paginationFactory = paginationFactory;
    }

    @Override
    public AccountDto createAccount(CreateAccountDto schema) throws AppException {
        return modelMapper.map(accountService.create(schema), AccountDto.class);
    }

    @Override
    public AccountDto getAccountById(Integer id) throws AppException {
        return modelMapper.map(accountService.getById(id), AccountDto.class);
    }

    @Override
    public AccountDto updateAccountById(UpdateAccountDto schema, Integer id) throws AppException {
        return modelMapper.map(accountService.updateById(id, schema), AccountDto.class);
    }

    @Override
    public void deleteAccountById(Integer id) throws AppException {
        accountService.deleteById(id);
    }

    @Override
    public AccountDto getAccountByLogin(String login) throws AppException {
        return modelMapper.map(accountService.getByLogin(login), AccountDto.class);
    }

    @Override
    public AccountDto updateAccountByLogin(UpdateAccountDto schema, String login) throws AppException {
        return modelMapper.map(accountService.updateByLogin(login, schema), AccountDto.class);
    }

    @Override
    public void deleteAccountByLogin(String login) throws AppException {
        accountService.deleteByLogin(login);
    }

    @Override
    public void updateAccountPasswordById(UpdateAccountPasswordDto schema, Integer id) throws AppException {
        accountService.updatePasswordById(id, schema);
    }

    @Override
    public Pagination<AccountDto> getAllAccounts(Integer page, Integer size) {
        return paginationFactory.createPagination(
                accountService.getAll(PageRequest.of(page, size)), AccountDto.class
        );
    }

    @Override
    public AccountDto getCurrentAccount(Authentication authentication) {
        var details = (AccountDetails)authentication.getPrincipal();
        return modelMapper.map(details.getAccount(), AccountDto.class);
    }
}
