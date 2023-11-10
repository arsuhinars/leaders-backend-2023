package org.catncode.leaders_backend.task.service;

import org.catncode.leaders_backend.task.entity.Task;

import java.util.List;

public interface TaskGenerationService {
    List<Task> generateTasks() throws Exception;
}
