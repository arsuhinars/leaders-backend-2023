package org.catncode.leaders_backend.employee.exception;

import org.catncode.leaders_backend.core.exception.EntityNotFoundException;
import org.springframework.http.HttpStatusCode;

public class EmployeeNotFoundException   extends EntityNotFoundException {
    public EmployeeNotFoundException() {
        super("Required Employee was not found");
    }

    public EmployeeNotFoundException(String details) {
        super(details);
    }

    public EmployeeNotFoundException(String details, HttpStatusCode statusCode) {
        super(details, statusCode);
    }
}
