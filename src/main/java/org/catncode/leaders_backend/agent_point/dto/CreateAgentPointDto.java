package org.catncode.leaders_backend.agent_point.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAgentPointDto {
    @NotBlank
    private String address;

    @NotNull
    private AgentPointJoinTime joinTime;

    @NotNull
    private Boolean materialsDelivered;

    @NotNull
    @Min(0)
    private Integer cardIssuanceDaysPassed;

    @NotNull
    @Min(0)
    private Integer approvedAppsCount;

    @NotNull
    @Min(0)
    private Integer issuedCardsCount;
}
