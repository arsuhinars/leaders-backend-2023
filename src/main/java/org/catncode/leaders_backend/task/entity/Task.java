package org.catncode.leaders_backend.task.entity;

import jakarta.persistence.*;
import lombok.*;
import org.catncode.leaders_backend.agent_point.entity.AgentPoint;
import org.catncode.leaders_backend.employee.entity.Employee;
import org.catncode.leaders_backend.task.dto.TaskType;

import java.time.LocalDate;
import java.time.OffsetTime;

@Entity
@Table(name = "task")
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PRIVATE)
    @ToString.Include
    private Integer id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "agent_point_id")
    private AgentPoint agentPoint;

    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskType type;

    @NonNull
    @Column(nullable = false)
    private LocalDate date;

    @NonNull
    @Column(nullable = false)
    private OffsetTime startTime;

    @NonNull
    @Column(nullable = false)
    private Double gettingTime;

    @NonNull
    @Column(nullable = false)
    private Double distanceTo;

    @NonNull
    @Column(nullable = false)
    private Integer orderNumber;
}
