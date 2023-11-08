package org.catncode.leaders_backend.task.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "task_status")
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode
public class TaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PRIVATE)
    @ToString.Include
    private Integer id;

    @NonNull
    @OneToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @NonNull
    @Column(nullable = false)
    private Boolean isCompleted;

    @NonNull
    @Column(nullable = false)
    private String comment;
}
