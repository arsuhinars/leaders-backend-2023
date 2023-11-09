package org.catncode.leaders_backend.task.controller;

import org.catncode.leaders_backend.core.dto.Pagination;
import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.task.dto.*;
import org.catncode.leaders_backend.task.entity.Task;
import org.catncode.leaders_backend.task.entity.TaskStatus;
import org.catncode.leaders_backend.task.service.TaskGenerationServiceImpl;
import org.catncode.leaders_backend.task.service.TaskManualServiceImpl;
import org.catncode.leaders_backend.task.service.TaskServiceImpl;
import org.catncode.leaders_backend.task.service.TaskStatusServiceImpl;

public class TaskControllerImpl implements TaskController{

    private final TaskServiceImpl taskServiceImpl;
    private final TaskGenerationServiceImpl taskGenerationServiceImpl;
    private final TaskManualServiceImpl taskManualServiceImpl;
    private final TaskStatusServiceImpl taskStatusServiceImpl;

    public TaskControllerImpl(TaskServiceImpl taskServiceImpl, TaskGenerationServiceImpl taskGenerationServiceImpl, TaskManualServiceImpl taskManualServiceImpl, TaskStatusServiceImpl taskStatusServiceImpl) {
        this.taskServiceImpl = taskServiceImpl;
        this.taskGenerationServiceImpl = taskGenerationServiceImpl;
        this.taskManualServiceImpl = taskManualServiceImpl;
        this.taskStatusServiceImpl = taskStatusServiceImpl;
    }

    @Override
    public DepartureTaskManual getDepartureTaskManual() {
        return taskManualServiceImpl.getDepartureTaskManual();
    }

    @Override
    public DepartureTaskManual updateDepartureTaskManual(DepartureTaskManual schema) {
        return taskManualServiceImpl.updateDepartureTaskManual(schema);
    }

    @Override
    public TuitionTaskManual getTuitionTaskManual() {
        return taskManualServiceImpl.getTuitionTaskManual();
    }

    @Override
    public TuitionTaskManual updateTuitionTaskManual(TuitionTaskManual schema) {
        return taskManualServiceImpl.updateTuitionTaskManual(schema);
    }

    @Override
    public DeliveryTaskManual getDeliveryTaskManual() {
        return taskManualServiceImpl.getDeliveryTaskManual();
    }

    @Override
    public DeliveryTaskManual updateDeliveryTaskManual(DeliveryTaskManual schema) {
        return taskManualServiceImpl.updateDeliveryTaskManual(schema);
    }

    @Override
    public void generateTasks() {

    }

    @Override
    public Task createTask(CreateTaskDto schema) {
        return taskServiceImpl.create(schema);
    }

    @Override
    public Pagination<TaskDto> getAllTasks(Integer page, Integer size, Integer employeeId, Integer agentPointId, Boolean archived) throws AppException {
        return null;
    }

    @Override
    public Task getTaskById(Integer id) throws AppException {
        return taskServiceImpl.getById(id);
    }

    @Override
    public Task updateTaskById(UpdateTaskDto schema, Integer id) throws AppException {
        return taskServiceImpl.updateById(schema,id);
    }

    @Override
    public void deleteTaskById(Integer id) throws AppException {
        taskServiceImpl.deleteById(id);
    }

    @Override
    public Task getTaskStatusById(Integer id) throws AppException {
        return taskServiceImpl.getById(id);
    }

    @Override
    public TaskStatus updateTaskStatusById(TaskStatusDto schema, Integer id) throws AppException {
        return taskStatusServiceImpl.updateById(schema, id);
    }
}
