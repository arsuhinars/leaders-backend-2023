package org.catncode.leaders_backend.task.factory;

import org.catncode.leaders_backend.agent_point.entity.AgentPoint;
import org.catncode.leaders_backend.task.dto.DepartureTaskManual;
import org.catncode.leaders_backend.task.dto.TaskType;
import org.catncode.leaders_backend.task.dto.TuitionTaskManual;
import org.catncode.leaders_backend.task.entity.Task;
import org.catncode.leaders_backend.task.service.TaskManualService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskFactory {
    private final TaskManualService taskManualService;

    public TaskFactory(TaskManualService taskManualService) {
        this.taskManualService = taskManualService;
    }

    public List<Task> createFromAgentPoints(List<AgentPoint> agentPoints) throws Exception {
        var departure = taskManualService.getByClass(DepartureTaskManual.class);
        var tuition = taskManualService.getByClass(TuitionTaskManual.class);
        var delivery = taskManualService.getByClass(DepartureTaskManual.class);

        var nowDate = LocalDate.now();

        var tasks = new ArrayList<Task>();
        for (var agentPoint : agentPoints) {
            if (departure.checkCondition(agentPoint)) {
                tasks.add(new Task(agentPoint, TaskType.DEPARTURE, nowDate));
            }
            else if (tuition.checkCondition(agentPoint)) {
                tasks.add(new Task(agentPoint, TaskType.TUITION, nowDate));
            }
            else if (delivery.checkCondition(agentPoint)) {
                tasks.add(new Task(agentPoint, TaskType.DELIVERY, nowDate));
            }
        }

        return tasks;
    }
}
