package org.catncode.leaders_backend.employee.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.catncode.leaders_backend.core.dto.Pagination;
import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.employee.dto.EmployeeDto;
import org.catncode.leaders_backend.employee.dto.UpdateEmployeeDto;
import org.catncode.leaders_backend.employee.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RequestMapping("/employees")
public interface EmployeeController {

    @GetMapping("/all")
    Pagination<EmployeeDto> getAllEmployees(
            //@RequestParam(defaultValue = "0") @Min(0) Integer page,
            //@RequestParam(defaultValue = "10") @Min(1) Integer size
            Pageable pageable
    );

    @GetMapping("/current")
    EmployeeDto getCurrentEmployee() throws AppException;

    @GetMapping("/{id}")
    Employee getEmployeeById(@PathVariable @NotNull @Min(1) Integer id) throws AppException;

    @PutMapping("/{id}")
    Employee updateEmployeeById(
            @Valid @RequestBody UpdateEmployeeDto schema, @PathVariable @NotNull @Min(1) Integer id
    ) throws AppException;
}
