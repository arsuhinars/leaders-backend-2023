package org.catncode.leaders_backend.task.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.catncode.leaders_backend.agent_point.dto.AgentPointJoinTime;
import org.catncode.leaders_backend.agent_point.entity.AgentPoint;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DeliveryTaskManual extends BaseTaskManual {
    @NotNull
    private AgentPointJoinTime joinTime;

    @Override
    public boolean checkCondition(AgentPoint agentPoint) {
        return !agentPoint.getMaterialsDelivered() || agentPoint.getJoinTime() == joinTime;
    }
}
