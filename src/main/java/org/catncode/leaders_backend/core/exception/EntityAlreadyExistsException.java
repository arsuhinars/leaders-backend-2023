package org.catncode.leaders_backend.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class EntityAlreadyExistsException extends AppException {
    public EntityAlreadyExistsException() {
        this("Given entity already exists");
    }

    public EntityAlreadyExistsException(String details) {
        this(details, HttpStatus.CONFLICT);
    }

    public EntityAlreadyExistsException(String details, HttpStatusCode statusCode) {
        super(details, statusCode);
    }
}
