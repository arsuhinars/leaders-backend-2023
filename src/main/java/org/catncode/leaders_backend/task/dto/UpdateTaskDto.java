package org.catncode.leaders_backend.task.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskDto {
    @NotNull
    private TaskType type;

    @NotNull
    @Min(1)
    private Integer agentPointId;

    @NotNull
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime startTime;

    @NotNull
    @Min(1)
    private Integer employeeId;

    @NotNull
    @Min(0)
    private Integer order;
}
