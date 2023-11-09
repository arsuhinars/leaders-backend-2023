package org.catncode.leaders_backend.task.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.catncode.leaders_backend.core.dto.Pagination;
import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.task.dto.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tasks")
public interface TaskController {
    @GetMapping("/departure/manual")
    DepartureTaskManual getDepartureTaskManual() throws Exception;

    @PutMapping("/departure/manual")
    DepartureTaskManual updateDepartureTaskManual(DepartureTaskManual schema) throws Exception;

    @GetMapping("/tuition/manual")
    TuitionTaskManual getTuitionTaskManual() throws Exception;

    @PutMapping("/tuition/manual")
    TuitionTaskManual updateTuitionTaskManual(TuitionTaskManual schema) throws Exception;

    @GetMapping("/delivery/manual")
    DeliveryTaskManual getDeliveryTaskManual() throws Exception;

    @PutMapping("/delivery/manual")
    DeliveryTaskManual updateDeliveryTaskManual(DeliveryTaskManual schema) throws Exception;

    @PostMapping("/generate")
    void generateTasks() throws Exception;

    @PostMapping
    TaskDto createTask(@Valid @RequestBody CreateTaskDto schema);

    @GetMapping("/all")
    Pagination<TaskDto> getAllTasks(
            Authentication authentication,
            @RequestParam(defaultValue = "0") @Min(0) Integer page,
            @RequestParam(defaultValue = "10") @Min(1) Integer size,
            @RequestParam(name = "employee_id", required = false) Integer employeeId,
            @RequestParam(name = "agent_point_id", required = false) Integer agentPointId,
            @RequestParam(name = "archived", defaultValue = "0") Boolean archived
    ) throws AppException;

    @GetMapping("/{id}")
    TaskDto getTaskById(Authentication authentication, @PathVariable @NotNull @Min(1) Integer id) throws AppException;

    @PutMapping("/{id}")
    TaskDto updateTaskById(
            @Valid @RequestBody UpdateTaskDto schema, @PathVariable @NotNull @Min(1) Integer id
    ) throws AppException;

    @DeleteMapping("/{id}")
    void deleteTaskById(@PathVariable @NotNull @Min(1) Integer id) throws AppException;

    @GetMapping("/{id}/status")
    TaskStatusDto getTaskStatusById(
            Authentication authentication,
            @PathVariable @NotNull @Min(1) Integer id
    ) throws AppException;

    @PutMapping("/{id}/status")
    TaskStatusDto updateTaskStatusById(
            Authentication authentication,
            @Valid @RequestBody TaskStatusDto schema,
            @PathVariable @NotNull @Min(1) Integer id
    ) throws AppException;
}
