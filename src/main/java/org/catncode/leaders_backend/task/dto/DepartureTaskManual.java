package org.catncode.leaders_backend.task.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DepartureTaskManual extends BaseTaskManual {
    @NotNull
    @Min(0)
    private Integer issuedCardsMinDaysCount1;

    @NotNull
    @Min(0)
    private Integer IssuedCardsMinDaysCount2;
}
