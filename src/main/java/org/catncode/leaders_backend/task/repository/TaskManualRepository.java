package org.catncode.leaders_backend.task.repository;

import org.catncode.leaders_backend.task.dto.TaskType;
import org.catncode.leaders_backend.task.entity.TaskManual;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TaskManualRepository extends CrudRepository<TaskManual, Integer> {
    Optional<TaskManual> findByType(TaskType type);
}
