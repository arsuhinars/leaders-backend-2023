package org.catncode.leaders_backend.task.service;

import org.catncode.leaders_backend.task.dto.DeliveryTaskManual;
import org.catncode.leaders_backend.task.dto.DepartureTaskManual;
import org.catncode.leaders_backend.task.dto.TuitionTaskManual;

public interface TaskManualService {
    DepartureTaskManual getDepartureTaskManual();

    DepartureTaskManual updateDepartureTaskManual(DepartureTaskManual dto);

    TuitionTaskManual getTuitionTaskManual();

    TuitionTaskManual updateTuitionTaskManual(TuitionTaskManual dto);

    DeliveryTaskManual getDeliveryTaskManual();

    DeliveryTaskManual updateDeliveryTaskManual(DeliveryTaskManual dto);
}
