package org.catncode.leaders_backend.task.service;

import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.task.dto.CreateTaskDto;
import org.catncode.leaders_backend.task.dto.UpdateTaskDto;
import org.catncode.leaders_backend.task.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    Task create(CreateTaskDto dto) throws AppException;

    Page<Task> getAll(Integer employeeId, Integer agentPointId, Boolean archived, Pageable pageable);

    Task getById(Integer id) throws AppException;

    Task updateById(UpdateTaskDto dto, Integer id) throws AppException;

    void deleteById(Integer id) throws AppException;

    /**
     * Метод обновления задач у сотрудника:
     * вычисляет новые расстояния и время прибытия к каждой задаче
     */
    void recalculateEmployeeTasks(Integer employeeId);
}
