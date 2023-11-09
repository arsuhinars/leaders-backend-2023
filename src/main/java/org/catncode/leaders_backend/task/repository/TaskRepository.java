package org.catncode.leaders_backend.task.repository;

import org.catncode.leaders_backend.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TaskRepository extends JpaSpecificationExecutor<Task>, JpaRepository<Task, Integer> {

}
