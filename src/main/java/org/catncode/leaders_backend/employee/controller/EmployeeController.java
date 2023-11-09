package org.catncode.leaders_backend.employee.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.catncode.leaders_backend.core.dto.Pagination;
import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.employee.dto.EmployeeDto;
import org.catncode.leaders_backend.employee.dto.UpdateEmployeeDto;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/employees")
public interface EmployeeController {
    @GetMapping("/all")
    Pagination<EmployeeDto> getAllEmployees(
            @RequestParam(defaultValue = "0") @Min(0) Integer page,
            @RequestParam(defaultValue = "10") @Min(1) Integer size
    );

    @GetMapping("/current")
    EmployeeDto getCurrentEmployee(Authentication authentication) throws AppException;

    @GetMapping("/{id}")
    EmployeeDto getEmployeeById(@PathVariable @NotNull @Min(1) Integer id) throws AppException;

    @PutMapping("/{id}")
    EmployeeDto updateEmployeeById(
            @Valid @RequestBody UpdateEmployeeDto schema, @PathVariable @NotNull @Min(1) Integer id
    ) throws AppException;
}
