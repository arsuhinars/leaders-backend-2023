package org.catncode.leaders_backend.employee.entity;

import org.catncode.leaders_backend.account.entity.Account;
import org.catncode.leaders_backend.employee.dto.EmployeeGrade;

public class Employee {
    private Integer id;
    private Account account;
    private EmployeeGrade grade;
    private String locationAddress;
}
