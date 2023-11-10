package org.catncode.leaders_backend.task.service;

import com.google.common.collect.ImmutableList;
import org.catncode.leaders_backend.agent_point.repository.AgentPointRepository;
import org.catncode.leaders_backend.task.entity.Task;
import org.catncode.leaders_backend.task.factory.TaskFactory;
import org.catncode.leaders_backend.task.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskGenerationServiceImpl implements TaskGenerationService {
    private final AgentPointRepository agentPointRepository;
    private final TaskRepository taskRepository;
    private final TaskFactory taskFactory;

    public TaskGenerationServiceImpl(
            AgentPointRepository agentPointRepository,
            TaskRepository taskRepository,
            TaskFactory taskFactory
    ) {
        this.agentPointRepository = agentPointRepository;
        this.taskRepository = taskRepository;
        this.taskFactory = taskFactory;
    }

    @Override
    public List<Task> generateTasks() throws Exception {
        // TODO archive old tasks

        var agentPoints = ImmutableList.copyOf(agentPointRepository.findAll());
        var tasks = taskFactory.createFromAgentPoints(agentPoints);

        taskRepository.saveAll(tasks);

        // TODO recalculate tasks

        return tasks;
    }
}
