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
public class DepartureTaskManual extends BaseTaskManual {
    @NotNull
    @Min(0)
    private Integer issuedCardsMinDaysCount1;

    @NotNull
    @Min(0)
    private Integer issuedCardsMinDaysCount2;

    @Override
    public boolean checkCondition(AgentPoint agentPoint) {
        var condition1 = (
                agentPoint.getCardIssuanceDaysPassed() > issuedCardsMinDaysCount1 &&
                agentPoint.getApprovedAppsCount() > 0
        );
        var condition2 = agentPoint.getCardIssuanceDaysPassed() > issuedCardsMinDaysCount2;

        return condition1 || condition2;
    }
}
