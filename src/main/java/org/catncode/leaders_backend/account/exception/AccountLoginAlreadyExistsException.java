package org.catncode.leaders_backend.account.exception;

import org.catncode.leaders_backend.core.exception.EntityAlreadyExistsException;
import org.springframework.http.HttpStatusCode;

public class AccountLoginAlreadyExistsException extends EntityAlreadyExistsException {
    public AccountLoginAlreadyExistsException() {
        super("Account with given login already exists");
    }

    public AccountLoginAlreadyExistsException(String details) {
        super(details);
    }

    public AccountLoginAlreadyExistsException(String details, HttpStatusCode statusCode) {
        super(details, statusCode);
    }
}
