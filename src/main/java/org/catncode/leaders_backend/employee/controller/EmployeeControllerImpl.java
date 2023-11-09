package org.catncode.leaders_backend.employee.controller;

import org.catncode.leaders_backend.core.dto.Pagination;
import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.core.factory.PaginationFactory;
import org.catncode.leaders_backend.employee.dto.EmployeeDto;
import org.catncode.leaders_backend.employee.dto.UpdateEmployeeDto;
import org.catncode.leaders_backend.employee.exception.EmployeeNotFoundException;
import org.catncode.leaders_backend.employee.service.EmployeeService;
import org.catncode.leaders_backend.security.dto.AccountDetails;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeControllerImpl implements EmployeeController{

    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;
    private final PaginationFactory paginationFactory;

    public EmployeeControllerImpl(
            EmployeeService employeeService,
            ModelMapper modelMapper,
            PaginationFactory paginationFactory
    ) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.paginationFactory = paginationFactory;
    }

    @Override
    public Pagination<EmployeeDto> getAllEmployees(Integer page, Integer size) {
        return paginationFactory.createPagination(
                employeeService.getAll(PageRequest.of(page, size)), EmployeeDto.class
        );
    }

    @Override
    public EmployeeDto getCurrentEmployee(Authentication authentication) throws AppException {
        var details = (AccountDetails)authentication.getPrincipal();
        var employee = details.getAccount().getEmployee();
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }

        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto getEmployeeById(Integer id) throws AppException {
        return modelMapper.map(employeeService.getById(id), EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployeeById(UpdateEmployeeDto schema, Integer id) throws AppException {
        return modelMapper.map(employeeService.updateById(id, schema), EmployeeDto.class);
    }
}
