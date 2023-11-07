package org.catncode.leaders_backend.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class ForbiddenException extends AppException {
    public ForbiddenException() {
        this("Access is denied");
    }

    public ForbiddenException(String details) {
        this(details, HttpStatus.FORBIDDEN);
    }

    public ForbiddenException(String details, HttpStatusCode statusCode) {
        super(details, statusCode);
    }
}
