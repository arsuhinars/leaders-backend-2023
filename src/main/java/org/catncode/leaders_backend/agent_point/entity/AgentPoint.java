package org.catncode.leaders_backend.agent_point.entity;

import jakarta.persistence.*;
import lombok.*;
import org.catncode.leaders_backend.agent_point.dto.AgentPointJoinTime;
import org.catncode.leaders_backend.task.entity.Task;

import java.util.HashSet;
import java.util.Set;

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
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AgentPointJoinTime joinTime;

    @NonNull
    @Column(nullable = false)
    private Boolean materialsDelivered;

    @NonNull
    @Column(nullable = false)
    private Integer cardIssuanceDaysPassed;

    @NonNull
    @Column(nullable = false)
    private Integer approvedAppsCount;

    @NonNull
    @Column(nullable = false)
    private Integer issuedCardsCount;

    @OneToMany(mappedBy = "agentPoint", cascade = { CascadeType.REMOVE })
    private Set<Task> taskSet = new HashSet<>();
}
