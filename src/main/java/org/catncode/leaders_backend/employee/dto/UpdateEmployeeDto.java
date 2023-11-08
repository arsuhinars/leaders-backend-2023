package org.catncode.leaders_backend.employee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeDto {
    @NotNull
    private EmployeeGrade grade;

    @NotBlank
    private String locationAddress;
}
