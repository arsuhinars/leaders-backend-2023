package org.catncode.leaders_backend.task.entity;

import jakarta.persistence.*;
import lombok.*;
import org.catncode.leaders_backend.task.dto.TaskType;

import java.util.Objects;

@Entity
@Table(name = "task_manual")
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString(onlyExplicitlyIncluded = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskManual that = (TaskManual) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
