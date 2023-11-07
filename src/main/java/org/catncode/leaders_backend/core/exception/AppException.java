package org.catncode.leaders_backend.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class AppException extends ResponseStatusException {
    private final String details;
    private final HttpStatusCode statusCode;

    public AppException(String details, HttpStatusCode statusCode) {
        super(statusCode, details);

        this.details = details;
        this.statusCode = statusCode;
    }
}
