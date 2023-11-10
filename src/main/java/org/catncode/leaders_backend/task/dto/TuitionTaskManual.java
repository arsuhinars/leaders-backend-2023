package org.catncode.leaders_backend.task.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.catncode.leaders_backend.agent_point.entity.AgentPoint;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TuitionTaskManual extends BaseTaskManual {
    @NotNull
    private Double approvedAppsPercentage;

    @NotNull
    @Min(0)
    private Integer issuedCardsCount;

    @Override
    public boolean checkCondition(AgentPoint agentPoint) {
        var p = (double)agentPoint.getIssuedCardsCount() / (double)agentPoint.getApprovedAppsCount() * 100.0;

        return agentPoint.getIssuedCardsCount() > issuedCardsCount && p < approvedAppsPercentage;
    }
}
