package org.catncode.leaders_backend.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class EntityConflictException extends AppException {
    public EntityConflictException() {
        this("There are entity conflict");
    }

    public EntityConflictException(String details) {
        this(details, HttpStatus.CONFLICT);
    }

    public EntityConflictException(String details, HttpStatusCode statusCode) {
        super(details, statusCode);
    }
}
