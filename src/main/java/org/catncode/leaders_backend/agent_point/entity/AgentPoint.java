package org.catncode.leaders_backend.agent_point.entity;

import org.catncode.leaders_backend.agent_point.dto.AgentPointJoinTime;

public class AgentPoint {
    private Integer id;
    private String address;
    private AgentPointJoinTime joinTime;
    private Boolean materialsDelivered;
    private Integer cardIssuanceDaysPassed;
    private Integer approvedAppsCount;
    private Integer issuedCardsCount;
}
