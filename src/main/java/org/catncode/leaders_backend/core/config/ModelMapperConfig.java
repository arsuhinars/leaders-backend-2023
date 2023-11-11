package org.catncode.leaders_backend.core.config;

import org.catncode.leaders_backend.core.exception.AppException;
import org.catncode.leaders_backend.task.dto.TaskDto;
import org.catncode.leaders_backend.task.dto.TaskType;
import org.catncode.leaders_backend.task.entity.Task;
import org.catncode.leaders_backend.task.service.TaskManualService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class ModelMapperConfig {
    @Autowired
    private TaskManualService taskManualService;

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.typeMap(Task.class, TaskDto.class).addMappings(
                mapper -> {
                    mapper.map(
                            task -> task.getEmployee().getAccount().getFullName(),
                            TaskDto::setEmployeeFullName
                    );
                    mapper.using(taskCompleteTimeConverter()).map(Task::getType, TaskDto::setCompleteTime);
                }
        );

        return modelMapper;
    }

    public Converter<TaskType, Double> taskCompleteTimeConverter() {
        return (ctx) -> taskManualService.getByType(ctx.getSource()).getPerformTime();
    }
}
