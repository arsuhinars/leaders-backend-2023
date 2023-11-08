package org.catncode.leaders_backend.task.service;

import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.task.dto.CreateTaskDto;
import org.catncode.leaders_backend.task.dto.UpdateTaskDto;
import org.catncode.leaders_backend.task.entity.Task;
import org.catncode.leaders_backend.task.exception.TaskNotFoundException;
import org.catncode.leaders_backend.task.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Task create(CreateTaskDto dto) throws AppException {
        return taskRepository.save(
                modelMapper.map(dto, Task.class)
        );
    }

    @Override
    public Page<Task> getAll(Integer employeeId, Integer agentPointId, Boolean archived, Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public Task getById(Integer id) throws AppException {
        return taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
    }

    @Override
    public Task updateById(UpdateTaskDto dto, Integer id) throws AppException {
        var task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        modelMapper.map(dto, task);
        return taskRepository.save(task);
    }

    @Override
    public void deleteById(Integer id) throws AppException {
        if(!taskRepository.existsById(id)){
            throw new TaskNotFoundException();
        }
        taskRepository.deleteById(id);
    }
}
