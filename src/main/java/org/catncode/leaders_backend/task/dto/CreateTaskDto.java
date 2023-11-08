package org.catncode.leaders_backend.task.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.OffsetTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskDto {
    @NotNull
    private TaskType type;

    @NotNull
    @Min(1)
    private Integer agentPointId;

    @NotNull
    private OffsetTime startTime;

    @NotNull
    @Min(1)
    private Integer employeeId;

    @NotNull
    @Min(0)
    private Integer order;
}
