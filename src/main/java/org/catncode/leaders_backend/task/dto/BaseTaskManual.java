package org.catncode.leaders_backend.task.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.catncode.leaders_backend.agent_point.entity.AgentPoint;
import org.catncode.leaders_backend.employee.dto.EmployeeGrade;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public abstract class BaseTaskManual {
    @NotNull
    private TaskPriority priority;

    @NotNull
    private Double performTime;

    @NotNull
    private EmployeeGrade requiredEmployeeGrade;

    public abstract boolean checkCondition(AgentPoint agentPoint);
}
