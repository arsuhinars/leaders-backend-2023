package org.catncode.leaders_backend.task.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TuitionTaskManual extends BaseTaskManual {
    @NotNull
    private Double approvedAppsPercentage;

    @NotNull
    @Min(0)
    private Integer issuedCardsCount;
}
