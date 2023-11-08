package org.catncode.leaders_backend.task.exception;

import org.catncode.leaders_backend.core.exception.EntityNotFoundException;
import org.springframework.http.HttpStatusCode;

public class TaskManualNotFoundException extends EntityNotFoundException {
    public TaskManualNotFoundException() {
        super("Required TaskManual was not found");
    }

    public TaskManualNotFoundException(String details) {
        super(details);
    }

    public TaskManualNotFoundException(String details, HttpStatusCode statusCode) {
        super(details, statusCode);
    }
}

