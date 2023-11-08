package org.catncode.leaders_backend.task.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.OffsetTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TaskDto {
    @NotNull
    @Min(1)
    private Integer id;

    @NotNull
    private TaskType type;

    @NotNull
    @Min(1)
    private Integer agentPointId;

    @NotBlank
    private String agentPointAddress;

    @NotNull
    private LocalDate  creationTime;

    @NotNull
    private OffsetTime startTime;

    @NotNull
    private Double gettingTime;

    @NotNull
    private Double distanceTo;

    @NotNull
    private Double completeTime;

    @NotNull
    @Min(1)
    private Integer employeeId;

    @NotBlank
    private String employeeFullName;

    @NotNull
    @Min(0)
    private Integer order;

    @NotNull
    private TaskStatusDto status;
}
