package org.catncode.leaders_backend.task.service;

import org.catncode.leaders_backend.task.dto.DeliveryTaskManual;
import org.catncode.leaders_backend.task.dto.DepartureTaskManual;
import org.catncode.leaders_backend.task.dto.TaskType;
import org.catncode.leaders_backend.task.dto.TuitionTaskManual;
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
    private final ModelMapper modelMapper;

    public TaskManualServiceImpl(TaskManualRepository taskManualRepository, ModelMapper modelMapper) {
        this.taskManualRepository = taskManualRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DepartureTaskManual getDepartureTaskManual() {
        TaskManual taskManual = taskManualRepository.findByType(TaskType.DEPARTURE).orElseThrow(TaskManualNotFoundException::new);
        return modelMapper.map(taskManual, DepartureTaskManual.class);
    }

    @Override
    public DepartureTaskManual updateDepartureTaskManual(DepartureTaskManual dto) {
        TaskManual taskManual = taskManualRepository.findByType(TaskType.DEPARTURE).orElseThrow(TaskManualNotFoundException::new);
        modelMapper.map(dto, taskManual);
        taskManualRepository.save(taskManual);
        return modelMapper.map(taskManual, DepartureTaskManual.class);
    }

    @Override
    public TuitionTaskManual getTuitionTaskManual() {
        TaskManual taskManual = taskManualRepository.findByType(TaskType.TUITION).orElseThrow(TaskManualNotFoundException::new);
        return modelMapper.map(taskManual, TuitionTaskManual.class);
    }

    @Override
    public TuitionTaskManual updateTuitionTaskManual(TuitionTaskManual dto) {
        TaskManual taskManual = taskManualRepository.findByType(TaskType.TUITION).orElseThrow(TaskManualNotFoundException::new);
        modelMapper.map(dto, taskManual);
        taskManualRepository.save(taskManual);
        return modelMapper.map(taskManual, TuitionTaskManual.class);
    }

    @Override
    public DeliveryTaskManual getDeliveryTaskManual() {
        TaskManual taskManual = taskManualRepository.findByType(TaskType.DELIVERY).orElseThrow(TaskManualNotFoundException::new);
        return modelMapper.map(taskManual, DeliveryTaskManual.class);
    }

    @Override
    public DeliveryTaskManual updateDeliveryTaskManual(DeliveryTaskManual dto) {
        TaskManual taskManual = taskManualRepository.findByType(TaskType.DELIVERY).orElseThrow(TaskManualNotFoundException::new);
        modelMapper.map(dto, taskManual);
        taskManualRepository.save(taskManual);
        return modelMapper.map(taskManual, DeliveryTaskManual.class);
    }
}
