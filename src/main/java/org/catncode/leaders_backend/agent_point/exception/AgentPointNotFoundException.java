package org.catncode.leaders_backend.agent_point.exception;

import org.catncode.leaders_backend.core.exception.EntityNotFoundException;
import org.springframework.http.HttpStatusCode;

public class AgentPointNotFoundException extends EntityNotFoundException {
    public AgentPointNotFoundException() {
        super("Required AgentPoint was not found");
    }

    public AgentPointNotFoundException(String details) {
        super(details);
    }

    public AgentPointNotFoundException(String details, HttpStatusCode statusCode) {
        super(details, statusCode);
    }
}
