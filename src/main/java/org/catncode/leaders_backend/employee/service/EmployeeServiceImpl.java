package org.catncode.leaders_backend.employee.service;

import org.catncode.leaders_backend.account.dto.AccountRole;
import org.catncode.leaders_backend.account.exception.AccountNotFoundException;
import org.catncode.leaders_backend.account.repository.AccountRepository;
import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.core.exception.EntityConflictException;
import org.catncode.leaders_backend.employee.dto.UpdateEmployeeDto;
import org.catncode.leaders_backend.employee.entity.Employee;
import org.catncode.leaders_backend.employee.exception.EmployeeNotFoundException;
import org.catncode.leaders_backend.employee.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final AccountRepository accountRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(
            AccountRepository accountRepository,
            EmployeeRepository employeeRepository,
            ModelMapper modelMapper
    ) {
        this.accountRepository = accountRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Employee getById(Integer id) throws AppException {
        return employeeRepository.findByAccountId(id).orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Page<Employee> getAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee updateById(Integer id, UpdateEmployeeDto dto) throws AppException {
        var account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
        if (account.getRole() != AccountRole.EMPLOYEE) {
            throw new EntityConflictException("Given account must have EMPLOYEE role");
        }

        Employee employee = account.getEmployee();
        if (employee == null) {
            employee = modelMapper.map(dto, Employee.class);
            employee.setAccount(account);
        } else {
            modelMapper.map(dto, employee);
        }

        return employeeRepository.save(employee);
    }
}
