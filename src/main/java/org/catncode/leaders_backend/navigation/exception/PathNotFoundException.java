package org.catncode.leaders_backend.navigation.exception;

import org.catncode.leaders_backend.core.exception.EntityNotFoundException;
import org.springframework.http.HttpStatusCode;

public class PathNotFoundException extends EntityNotFoundException {
    public PathNotFoundException() {
        super("Required path was not found");
    }

    public PathNotFoundException(String details) {
        super(details);
    }

    public PathNotFoundException(String details, HttpStatusCode statusCode) {
        super(details, statusCode);
    }
}
