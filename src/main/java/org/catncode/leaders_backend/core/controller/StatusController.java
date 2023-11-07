package org.catncode.leaders_backend.core.controller;

import org.catncode.leaders_backend.core.dto.StatusResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
    @GetMapping("/status")
    public StatusResponse getStatus() {
        return new StatusResponse("ok");
    }
}
