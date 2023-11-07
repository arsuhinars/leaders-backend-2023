package org.catncode.leaders_backend.task.entity;

import org.catncode.leaders_backend.agent_point.entity.AgentPoint;
import org.catncode.leaders_backend.employee.entity.Employee;
import org.catncode.leaders_backend.task.dto.TaskType;

import java.time.LocalDate;
import java.time.OffsetTime;

public class Task {
    private Integer id;
    private TaskType type;
    private AgentPoint agentPoint;
    private LocalDate date;
    private OffsetTime startTime;
    private Double gettingTime;
    private Double distanceTo;
    private Employee employee;
    private Integer order;
}
