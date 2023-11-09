package org.catncode.leaders_backend.task.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.catncode.leaders_backend.task.dto.*;

public interface TaskManualService {
    static <T extends BaseTaskManual> TaskType getTypeByManual(Class<T> type) {
        if (type == DeliveryTaskManual.class) {
            return TaskType.DELIVERY;
        } else if (type == TuitionTaskManual.class) {
            return TaskType.TUITION;
        } else if (type == DepartureTaskManual.class) {
            return TaskType.DEPARTURE;
        }

        throw new IllegalArgumentException();
    }

    <T extends BaseTaskManual> T getTaskManual(Class<T> type) throws JsonProcessingException;

    <T extends BaseTaskManual> T updateTaskManual(Class<T> type, T dto) throws JsonProcessingException;
}
