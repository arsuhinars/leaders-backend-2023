package org.catncode.leaders_backend.task.service;

import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.task.dto.TaskStatusDto;
import org.catncode.leaders_backend.task.entity.TaskStatus;
import org.catncode.leaders_backend.task.exception.TaskNotFoundException;
import org.catncode.leaders_backend.task.exception.TaskStatusNotFoundException;
import org.catncode.leaders_backend.task.repository.TaskRepository;
import org.catncode.leaders_backend.task.repository.TaskStatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskStatusServiceImpl implements TaskStatusService {
    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final ModelMapper modelMapper;

    public TaskStatusServiceImpl(
            TaskRepository taskRepository,
            TaskStatusRepository taskStatusRepository,
            ModelMapper modelMapper
    ) {
        this.taskRepository = taskRepository;
        this.taskStatusRepository = taskStatusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TaskStatus getById(Integer id) throws AppException {
        return taskStatusRepository.findByTaskId(id).orElseThrow(TaskStatusNotFoundException::new);
    }

    @Override
    public TaskStatus updateById(TaskStatusDto dto, Integer id) throws AppException {
        var task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);

        TaskStatus status = task.getStatus();
        if (status == null) {
            status = modelMapper.map(dto, TaskStatus.class);
            status.setTask(task);
        } else {
            modelMapper.map(dto, status);
        }

        return taskStatusRepository.save(status);
    }
}
