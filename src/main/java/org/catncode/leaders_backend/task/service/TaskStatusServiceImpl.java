package org.catncode.leaders_backend.task.service;

import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.task.dto.TaskStatusDto;
import org.catncode.leaders_backend.task.entity.TaskStatus;
import org.catncode.leaders_backend.task.exception.TaskStatusNotFoundException;
import org.catncode.leaders_backend.task.repository.TaskStatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskStatusServiceImpl implements TaskStatusService{
    private final TaskStatusRepository taskStatusRepository;
    private final ModelMapper modelMapper;

    public TaskStatusServiceImpl(TaskStatusRepository taskStatusRepository, ModelMapper modelMapper) {
        this.taskStatusRepository = taskStatusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TaskStatus getById(Integer id) throws AppException {
        return taskStatusRepository.findById(id).orElseThrow(TaskStatusNotFoundException::new);
    }

    @Override
    public TaskStatus updateById(TaskStatusDto dto, Integer id) throws AppException {
        var taskStatus = taskStatusRepository.findById(id).orElseThrow(TaskStatusNotFoundException::new);
        modelMapper.map(dto, taskStatus);
        return taskStatusRepository.save(taskStatus);
    }
}
