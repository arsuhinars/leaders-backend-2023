package org.catncode.leaders_backend.task.entity;

import jakarta.persistence.*;
import lombok.*;
import org.catncode.leaders_backend.task.dto.BaseTaskManual;
import org.catncode.leaders_backend.task.dto.TaskType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "task_manual")
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode
public class TaskManual {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PRIVATE)
    @ToString.Include
    private Integer id;

    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskType type;

    @NonNull
    @Column(nullable = false)
    private String jsonManual;
}
