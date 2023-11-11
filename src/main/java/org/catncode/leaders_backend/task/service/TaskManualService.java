package org.catncode.leaders_backend.task.service;

import org.catncode.leaders_backend.core.exception.ApiException;
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

    BaseTaskManual getByType(TaskType type) throws ApiException;

    <T extends BaseTaskManual> T getByClass(Class<T> type) throws ApiException;

    <T extends BaseTaskManual> T updateByClass(Class<T> type, T dto) throws ApiException;
}
