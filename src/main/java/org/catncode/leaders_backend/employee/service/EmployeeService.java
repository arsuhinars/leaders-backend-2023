package org.catncode.leaders_backend.employee.service;

import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    Employee getById(Integer id) throws AppException;

    Page<Employee> getAll(Pageable pageable);

    Employee updateById(Integer id) throws AppException;
}
