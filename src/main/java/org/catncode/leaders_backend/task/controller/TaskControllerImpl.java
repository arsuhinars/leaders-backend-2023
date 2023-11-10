package org.catncode.leaders_backend.task.controller;

import org.catncode.leaders_backend.account.dto.AccountRole;
import org.catncode.leaders_backend.core.dto.Pagination;
import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.core.exception.ForbiddenException;
import org.catncode.leaders_backend.core.factory.PaginationFactory;
import org.catncode.leaders_backend.security.dto.AccountDetails;
import org.catncode.leaders_backend.task.dto.*;
import org.catncode.leaders_backend.task.entity.Task;
import org.catncode.leaders_backend.task.entity.TaskStatus;
import org.catncode.leaders_backend.task.exception.TaskNotFoundException;
import org.catncode.leaders_backend.task.exception.TaskStatusNotFoundException;
import org.catncode.leaders_backend.task.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskControllerImpl implements TaskController {

    private final TaskService taskService;
    private final TaskGenerationService taskGenerationService;
    private final TaskManualService taskManualService;
    private final TaskStatusService taskStatusService;
    private final ModelMapper modelMapper;
    private final PaginationFactory paginationFactory;

    public TaskControllerImpl(
            TaskService taskService,
            TaskGenerationService taskGenerationService,
            TaskManualService taskManualService,
            TaskStatusService taskStatusService,
            ModelMapper modelMapper,
            PaginationFactory paginationFactory
    ) {
        this.taskService = taskService;
        this.taskGenerationService = taskGenerationService;
        this.taskManualService = taskManualService;
        this.taskStatusService = taskStatusService;
        this.modelMapper = modelMapper;
        this.paginationFactory = paginationFactory;
    }

    @Override
    public DepartureTaskManual getDepartureTaskManual() throws Exception {
        return taskManualService.getTaskManual(DepartureTaskManual.class);
    }

    @Override
    public DepartureTaskManual updateDepartureTaskManual(DepartureTaskManual schema) throws Exception {
        return taskManualService.updateTaskManual(DepartureTaskManual.class, schema);
    }

    @Override
    public TuitionTaskManual getTuitionTaskManual() throws Exception {
        return taskManualService.getTaskManual(TuitionTaskManual.class);
    }

    @Override
    public TuitionTaskManual updateTuitionTaskManual(TuitionTaskManual schema) throws Exception {
        return taskManualService.updateTaskManual(TuitionTaskManual.class, schema);
    }

    @Override
    public DeliveryTaskManual getDeliveryTaskManual() throws Exception {
        return taskManualService.getTaskManual(DeliveryTaskManual.class);
    }

    @Override
    public DeliveryTaskManual updateDeliveryTaskManual(DeliveryTaskManual schema) throws Exception {
        return taskManualService.updateTaskManual(DeliveryTaskManual.class, schema);
    }

    @Override
    public void generateTasks() throws Exception {
        taskGenerationService.generateTasks();
    }

    @Override
    public TaskDto createTask(CreateTaskDto schema) {
        return modelMapper.map(taskService.create(schema), TaskDto.class);
    }

    @Override
    public Pagination<TaskDto> getAllTasks(
            Authentication authentication,
            Integer page,
            Integer size,
            Integer employeeId,
            Integer agentPointId,
            Boolean archived
    ) throws AppException {
        var details = (AccountDetails)authentication.getPrincipal();
        var account = details.getAccount();
        var pageable = PageRequest.of(page, size);

        Page<Task> tasks;
        if (account.getRole() == AccountRole.EMPLOYEE) {
            tasks = taskService.getAll(account.getId(), null, false, pageable);
        } else {
            tasks = taskService.getAll(employeeId, agentPointId, archived, pageable);
        }

        return paginationFactory.createPagination(tasks, TaskDto.class);
    }

    @Override
    public TaskDto getTaskById(Authentication authentication, Integer id) throws AppException {
        var details = (AccountDetails)authentication.getPrincipal();
        var account = details.getAccount();

        Task task;
        try {
            task = taskService.getById(id);
        } catch (TaskNotFoundException exception) {
            if (account.getRole() == AccountRole.EMPLOYEE) {
                throw new ForbiddenException();
            }
            throw exception;
        }

        if (account.getRole() == AccountRole.EMPLOYEE && !task.getEmployee().getAccount().equals(account)) {
            throw new ForbiddenException();
        }

        return modelMapper.map(task, TaskDto.class);
    }

    @Override
    public TaskDto updateTaskById(UpdateTaskDto schema, Integer id) throws AppException {
        return modelMapper.map(taskService.updateById(schema, id), TaskDto.class);
    }

    @Override
    public void deleteTaskById(Integer id) throws AppException {
        taskService.deleteById(id);
    }

    @Override
    public TaskStatusDto getTaskStatusById(Authentication authentication, Integer id) throws AppException {
        var details = (AccountDetails)authentication.getPrincipal();
        var account = details.getAccount();

        TaskStatus taskStatus;
        try {
            taskStatus = taskStatusService.getById(id);
        } catch (TaskStatusNotFoundException exception) {
            if (account.getRole() == AccountRole.EMPLOYEE) {
                throw new ForbiddenException();
            }
            throw exception;
        }

        if (account.getRole() == AccountRole.EMPLOYEE &&
                !taskStatus.getTask().getEmployee().getAccount().equals(account)) {
            throw new ForbiddenException();
        }

        return modelMapper.map(taskStatus, TaskStatusDto.class);
    }

    @Override
    public TaskStatusDto updateTaskStatusById(
            Authentication authentication,
            TaskStatusDto schema,
            Integer id
    ) throws AppException {
        var details = (AccountDetails)authentication.getPrincipal();
        var account = details.getAccount();

        TaskStatus taskStatus;
        try {
            taskStatus = taskStatusService.updateById(schema, id);
        } catch (TaskStatusNotFoundException exception) {
            if (account.getRole() == AccountRole.EMPLOYEE) {
                throw new ForbiddenException();
            }
            throw exception;
        }

        if (account.getRole() == AccountRole.EMPLOYEE &&
                !taskStatus.getTask().getEmployee().getAccount().equals(account)) {
            throw new ForbiddenException();
        }

        return modelMapper.map(taskStatus, TaskStatusDto.class);
    }
}
