package org.catncode.leaders_backend.task.service;

import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.task.dto.TaskStatusDto;
import org.catncode.leaders_backend.task.entity.TaskStatus;

public interface TaskStatusService {
    TaskStatus getById(Integer id) throws AppException;

    TaskStatus updateById(TaskStatusDto dto, Integer id) throws AppException;
}
