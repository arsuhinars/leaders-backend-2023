package org.catncode.leaders_backend.task.exception;

import org.catncode.leaders_backend.core.exception.EntityNotFoundException;
import org.springframework.http.HttpStatusCode;

public class TaskStatusNotFoundException extends EntityNotFoundException {
    public TaskStatusNotFoundException() {
        super("Required TaskStatus was not found");
    }

    public TaskStatusNotFoundException(String details) {
        super(details);
    }

    public TaskStatusNotFoundException(String details, HttpStatusCode statusCode) {
        super(details, statusCode);
    }
}

