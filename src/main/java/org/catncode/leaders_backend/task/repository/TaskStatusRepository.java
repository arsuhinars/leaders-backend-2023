package org.catncode.leaders_backend.task.repository;

import org.catncode.leaders_backend.task.entity.Task;
import org.catncode.leaders_backend.task.entity.TaskStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TaskStatusRepository extends CrudRepository<TaskStatus, Integer> {
    Optional<TaskStatus> findByTask(Task task);
}
