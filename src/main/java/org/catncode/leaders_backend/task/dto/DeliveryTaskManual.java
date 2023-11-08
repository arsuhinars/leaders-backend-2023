package org.catncode.leaders_backend.task.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.catncode.leaders_backend.agent_point.dto.AgentPointJoinTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DeliveryTaskManual extends BaseTaskManual {
    @NotNull
    private AgentPointJoinTime joinTime;
}
