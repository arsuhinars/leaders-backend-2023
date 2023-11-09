package org.catncode.leaders_backend.task.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.catncode.leaders_backend.task.dto.*;
import org.catncode.leaders_backend.task.entity.TaskManual;
import org.catncode.leaders_backend.task.exception.TaskManualNotFoundException;
import org.catncode.leaders_backend.task.repository.TaskManualRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskManualServiceImpl implements TaskManualService{
    private final TaskManualRepository taskManualRepository;
    private final ObjectMapper objectMapper;

    public TaskManualServiceImpl(TaskManualRepository taskManualRepository, ObjectMapper objectMapper) {
        this.taskManualRepository = taskManualRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public <T extends BaseTaskManual> T getTaskManual(Class<T> type) throws JsonProcessingException {
        var taskType = TaskManualService.getTypeByManual(type);
        var taskManual = taskManualRepository.findByType(taskType)
                .orElseThrow(TaskManualNotFoundException::new);

        return objectMapper.readValue(taskManual.getJsonManual(), type);
    }

    @Override
    public <T extends BaseTaskManual> T updateTaskManual(Class<T> type, T dto) throws JsonProcessingException {
        var taskType = TaskManualService.getTypeByManual(type);
        var taskManual = taskManualRepository.findByType(taskType)
                .orElseGet(() -> new TaskManual(taskType, ""));

        taskManual.setJsonManual(objectMapper.writeValueAsString(dto));
        taskManualRepository.save(taskManual);

        return dto;
    }
}
