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
import org.catncode.leaders_backend.navigation.service.DistanceMatrixService;
import org.catncode.leaders_backend.navigation.service.GeocodeService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final AccountRepository accountRepository;
    private final EmployeeRepository employeeRepository;
    private final GeocodeService geocodeService;
    private final DistanceMatrixService distanceMatrixService;
    private final ModelMapper modelMapper;

    private final TransactionTemplate transactionTemplate;

    public EmployeeServiceImpl(
            AccountRepository accountRepository,
            EmployeeRepository employeeRepository,
            GeocodeService geocodeService,
            DistanceMatrixService distanceMatrixService,
            PlatformTransactionManager transactionManager,
            ModelMapper modelMapper
    ) {
        this.accountRepository = accountRepository;
        this.employeeRepository = employeeRepository;
        this.geocodeService = geocodeService;
        this.distanceMatrixService = distanceMatrixService;
        this.modelMapper = modelMapper;

        this.transactionTemplate = new TransactionTemplate(transactionManager);
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

        boolean addressChanged = false;
        Employee employee;
        if (account.getEmployee() == null) {
            employee = modelMapper.map(dto, Employee.class);
            employee.setAccount(account);
            addressChanged = true;
        } else {
            employee = account.getEmployee();
            addressChanged = !employee.getLocationAddress().equals(dto.getLocationAddress());
            modelMapper.map(dto, employee);
        }

        if (addressChanged) {
            employee.setLocation(geocodeService.createLocationByAddress(dto.getLocationAddress()));
        }

        transactionTemplate.execute(status -> {
            employeeRepository.save(employee);
            return employee.getId();
        });

        if (addressChanged) {
            distanceMatrixService.recalculateFor(employee.getLocation());
        }

        return employee;
    }
}
