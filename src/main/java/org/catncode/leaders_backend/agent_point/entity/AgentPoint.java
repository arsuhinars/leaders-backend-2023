package org.catncode.leaders_backend.agent_point.entity;

import jakarta.persistence.*;
import lombok.*;
import org.catncode.leaders_backend.agent_point.dto.AgentPointJoinTime;

@Entity
@Table(name = "agent_point")
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode
public class AgentPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PRIVATE)
    @ToString.Include
    private Integer id;

    @NonNull
    @Column(nullable = false)
    private String address;

    @NonNull
    @Column(name = "join_time", nullable = false)
    @Enumerated(EnumType.STRING)
    private AgentPointJoinTime joinTime;

    @NonNull
    @Column(name = "materials_delivered", nullable = false)
    private Boolean materialsDelivered;

    @NonNull
    @Column(name = "card_issuance_days_passed", nullable = false)
    private Integer cardIssuanceDaysPassed;

    @NonNull
    @Column(name = "approved_apps_count", nullable = false)
    private Integer approvedAppsCount;

    @NonNull
    @Column(name = "issued_cards_count", nullable = false)
    private Integer issuedCardsCount;
}
