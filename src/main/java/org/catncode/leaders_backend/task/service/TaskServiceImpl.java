package org.catncode.leaders_backend.task.service;

import org.catncode.leaders_backend.agent_point.exception.AgentPointNotFoundException;
import org.catncode.leaders_backend.agent_point.repository.AgentPointRepository;
import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.employee.exception.EmployeeNotFoundException;
import org.catncode.leaders_backend.employee.repository.EmployeeRepository;
import org.catncode.leaders_backend.navigation.service.DistanceMatrixService;
import org.catncode.leaders_backend.task.dto.CreateTaskDto;
import org.catncode.leaders_backend.task.dto.UpdateTaskDto;
import org.catncode.leaders_backend.task.entity.Task;
import org.catncode.leaders_backend.task.exception.TaskNotFoundException;
import org.catncode.leaders_backend.task.repository.TaskRepository;
import org.catncode.leaders_backend.task.specification.TaskSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final AgentPointRepository agentPointRepository;
    private final DistanceMatrixService distanceMatrixService;
    private final ModelMapper modelMapper;

    public TaskServiceImpl(
            TaskRepository taskRepository,
            EmployeeRepository employeeRepository,
            AgentPointRepository agentPointRepository,
            DistanceMatrixService distanceMatrixService,
            ModelMapper modelMapper
    ) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
        this.agentPointRepository = agentPointRepository;
        this.distanceMatrixService = distanceMatrixService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Task create(CreateTaskDto dto) throws AppException {
        var agentPoint = agentPointRepository.findById(dto.getAgentPointId())
                .orElseThrow(AgentPointNotFoundException::new);
        var employee = employeeRepository.findByAccountId(dto.getEmployeeId())
                .orElseThrow(EmployeeNotFoundException::new);

        var task = modelMapper.map(dto, Task.class);
        task.setAgentPoint(agentPoint);
        task.setEmployee(employee);
        task.setCreationTime(LocalDate.now());

        taskRepository.save(task);
        recalculateEmployeeTasks(employee.getAccount().getId());

        return taskRepository.findById(task.getId()).orElseThrow();
    }

    @Override
    public Page<Task> getAll(Integer employeeId, Integer agentPointId, Boolean archived, Pageable pageable) {
        var specification = TaskSpecification.filterTasks(employeeId, agentPointId, archived);
        return taskRepository.findAll(specification, pageable);
    }

    @Override
    public Task getById(Integer id) throws AppException {
        return taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
    }

    @Override
    public Task updateById(UpdateTaskDto dto, Integer id) throws AppException {
        var agentPoint = agentPointRepository.findById(dto.getAgentPointId())
                .orElseThrow(AgentPointNotFoundException::new);
        var employee = employeeRepository.findByAccountId(dto.getEmployeeId())
                .orElseThrow(EmployeeNotFoundException::new);

        var task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        modelMapper.map(dto, task);

        task.setAgentPoint(agentPoint);
        task.setEmployee(employee);

        taskRepository.save(task);
        recalculateEmployeeTasks(employee.getId());

        return taskRepository.findById(task.getId()).orElseThrow();
    }

    @Override
    public void deleteById(Integer id) throws AppException {
        if (!taskRepository.existsById(id)){
            throw new TaskNotFoundException();
        }
        taskRepository.deleteById(id);
    }

    @Override
    public void recalculateEmployeeTasks(Integer employeeId) {
        var employee = employeeRepository.findByAccountId(employeeId)
                .orElseThrow(EmployeeNotFoundException::new);
        var startLoc = employee.getLocation();
        var tasks = taskRepository.findAll(
                TaskSpecification.filterTasks(employeeId, null, false)
        );

        for (int i = 0; i < tasks.size(); ++i) {
            var task = tasks.get(i);
            var currLoc = task.getAgentPoint().getLocation();
            var prevLoc = (
                    i == 0 ?
                    startLoc :
                    tasks.get(i - 1).getAgentPoint().getLocation()
            );

            var path = distanceMatrixService.getDistanceBetween(prevLoc, currLoc);

            task.setDistanceTo(path.getMeters());
            task.setGettingTime(path.getHours());
        }

        taskRepository.saveAll(tasks);
    }
}
