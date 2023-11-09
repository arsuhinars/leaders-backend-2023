package org.catncode.leaders_backend.task.repository;

import org.catncode.leaders_backend.task.entity.Task;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskRepository extends CrudRepository<Task, Integer>, JpaSpecificationExecutor<Task>, PagingAndSortingRepository<Task, Integer> {

}
