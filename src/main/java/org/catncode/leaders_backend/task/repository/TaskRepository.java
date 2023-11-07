package org.catncode.leaders_backend.task.repository;

import org.catncode.leaders_backend.task.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {

}
