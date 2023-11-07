package org.catncode.leaders_backend.core.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class StatusResponse {
    @NonNull
    private String status;
}
