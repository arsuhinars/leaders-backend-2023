package org.catncode.leaders_backend.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class EntityNotFoundException extends AppException {
    public EntityNotFoundException() {
        this("Required entity was not found");
    }

    public EntityNotFoundException(String details) {
        this(details, HttpStatus.NOT_FOUND);
    }

    public EntityNotFoundException(String details, HttpStatusCode statusCode) {
        super(details, statusCode);
    }
}
