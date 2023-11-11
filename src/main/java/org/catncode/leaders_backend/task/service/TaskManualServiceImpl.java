package org.catncode.leaders_backend.task.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.catncode.leaders_backend.core.exception.ApiException;
import org.catncode.leaders_backend.task.dto.*;
import org.catncode.leaders_backend.task.entity.TaskManual;
import org.catncode.leaders_backend.task.exception.TaskManualNotFoundException;
import org.catncode.leaders_backend.task.repository.TaskManualRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskManualServiceImpl implements TaskManualService {
    private final TaskManualRepository taskManualRepository;
    private final ObjectMapper objectMapper;

    public TaskManualServiceImpl(TaskManualRepository taskManualRepository, ObjectMapper objectMapper) {
        this.taskManualRepository = taskManualRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public BaseTaskManual getByType(TaskType type) throws ApiException {
        switch (type) {
            case DEPARTURE -> {
                return getByClass(DepartureTaskManual.class);
            }
            case TUITION -> {
                return getByClass(TuitionTaskManual.class);
            }
            case DELIVERY -> {
                return getByClass(DeliveryTaskManual.class);
            }
        }

        throw new IllegalArgumentException();
    }

    @Override
    public <T extends BaseTaskManual> T getByClass(Class<T> type) throws ApiException {
        var taskType = TaskManualService.getTypeByManual(type);
        var taskManual = taskManualRepository.findByType(taskType)
                .orElseThrow(TaskManualNotFoundException::new);

        try {
            return objectMapper.readValue(taskManual.getJsonManual(), type);
        }
        catch (JsonProcessingException ex) {
            throw new ApiException(ex.getMessage());
        }
    }

    @Override
    public <T extends BaseTaskManual> T updateByClass(Class<T> type, T dto) throws ApiException {
        var taskType = TaskManualService.getTypeByManual(type);
        var taskManual = taskManualRepository.findByType(taskType)
                .orElseGet(() -> new TaskManual(taskType, ""));

        try {
            taskManual.setJsonManual(objectMapper.writeValueAsString(dto));
        }
        catch (JsonProcessingException ex) {
            throw new ApiException(ex.getMessage());
        }
        taskManualRepository.save(taskManual);

        return dto;
    }
}
