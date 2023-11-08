package org.catncode.leaders_backend.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TaskStatusDto {
    @NotNull
    private Boolean isCompleted;

    @NotBlank
    private String comment;
}
