package org.catncode.leaders_backend.task.entity;

import jakarta.persistence.*;
import lombok.*;
import org.catncode.leaders_backend.agent_point.entity.AgentPoint;
import org.catncode.leaders_backend.employee.entity.Employee;
import org.catncode.leaders_backend.task.dto.TaskType;

import java.time.LocalDate;
import java.time.OffsetTime;
import java.util.Objects;

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
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PRIVATE)
    @ToString.Include
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agent_point_id", nullable = false)
    private AgentPoint agentPoint;

    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskType type;

    @NonNull
    @Column(nullable = false)
    private LocalDate creationTime;

    @Column(nullable = false)
    private OffsetTime startTime;

    @Column(nullable = false)
    private Double gettingTime = 0.0;

    @Column(nullable = false)
    private Double distanceTo = 0.0;

    @Column(nullable = false)
    private Integer orderNumber = 0;

    @Column(nullable = false)
    private Boolean isArchived = false;

    @OneToOne(mappedBy = "task", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
    private TaskStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
