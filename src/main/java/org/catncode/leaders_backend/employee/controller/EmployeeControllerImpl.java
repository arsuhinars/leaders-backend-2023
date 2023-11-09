package org.catncode.leaders_backend.employee.controller;

import org.catncode.leaders_backend.core.dto.Pagination;
import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.employee.dto.EmployeeDto;
import org.catncode.leaders_backend.employee.dto.UpdateEmployeeDto;
import org.catncode.leaders_backend.employee.entity.Employee;
import org.catncode.leaders_backend.employee.service.EmployeeServiceImpl;

import java.awt.print.Pageable;

public class EmployeeControllerImpl implements EmployeeController{

    private final EmployeeServiceImpl employeeServiceImpl;
    EmployeeDto employeeDto;

    public EmployeeControllerImpl(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @Override
    public Pagination<EmployeeDto> getAllEmployees(Pageable pageable) {
        return null;
    }

    @Override
    public EmployeeDto getCurrentEmployee() throws AppException {
        return employeeDto;
    }

    @Override
    public Employee getEmployeeById(Integer id) throws AppException {
        return employeeServiceImpl.getById(id);
    }

    @Override
    public Employee updateEmployeeById(UpdateEmployeeDto schema, Integer id) throws AppException {
        return employeeServiceImpl.updateById(id, schema);
    }
}
