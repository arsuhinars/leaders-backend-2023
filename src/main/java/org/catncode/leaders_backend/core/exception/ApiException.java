package org.catncode.leaders_backend.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class ApiException extends AppException {
    public ApiException() {
        this("Error occurred while doing external API call");
    }

    public ApiException(String details) {
        this(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ApiException(String details, HttpStatusCode statusCode) {
        super(details, statusCode);
    }
}
