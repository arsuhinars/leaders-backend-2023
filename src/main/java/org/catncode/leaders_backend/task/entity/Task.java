package org.catncode.leaders_backend.task.entity;

import jakarta.persistence.*;
import lombok.*;
import org.catncode.leaders_backend.agent_point.entity.AgentPoint;
import org.catncode.leaders_backend.employee.entity.Employee;
import org.catncode.leaders_backend.task.dto.TaskType;

import java.time.LocalDate;
import java.time.OffsetTime;

@Entity
@Table(name = "task", indexes = {
        @Index(columnList = "employee_id"),
        @Index(columnList = "agent_point_id"),
        @Index(columnList = "orderNumber")
})
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

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "agent_point_id", nullable = false)
    private AgentPoint agentPoint;

    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskType type;

    @NonNull
    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private OffsetTime startTime;

    @Column(nullable = false)
    private Double gettingTime;

    @Column(nullable = false)
    private Double distanceTo;

    @Column(nullable = false)
    private Integer orderNumber;

    @Column(nullable = false)
    private Boolean isArchived = false;

    @OneToOne(mappedBy = "task", cascade = { CascadeType.REMOVE })
    private TaskStatus status;
}
