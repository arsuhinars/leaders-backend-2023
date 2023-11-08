package org.catncode.leaders_backend.task.exception;

import org.catncode.leaders_backend.core.exception.EntityNotFoundException;
import org.springframework.http.HttpStatusCode;

public class TaskNotFoundException   extends EntityNotFoundException {
    public TaskNotFoundException() {
        super("Required Task was not found");
    }

    public TaskNotFoundException(String details) {
        super(details);
    }

    public TaskNotFoundException(String details, HttpStatusCode statusCode) {
        super(details, statusCode);
    }
}
