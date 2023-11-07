package org.catncode.leaders_backend.account.exception;

import org.catncode.leaders_backend.core.exception.EntityNotFoundException;
import org.springframework.http.HttpStatusCode;

public class AccountNotFoundException extends EntityNotFoundException {
    public AccountNotFoundException() {
        super("Required Account was not found");
    }

    public AccountNotFoundException(String details) {
        super(details);
    }

    public AccountNotFoundException(String details, HttpStatusCode statusCode) {
        super(details, statusCode);
    }
}
