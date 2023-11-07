package org.catncode.leaders_backend.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class InvalidFormatException extends AppException {
    public InvalidFormatException() {
        this("Invalid input format");
    }

    public InvalidFormatException(String details) {
        this(details, HttpStatus.BAD_REQUEST);
    }

    public InvalidFormatException(String details, HttpStatusCode statusCode) {
        super(details, statusCode);
    }
}
