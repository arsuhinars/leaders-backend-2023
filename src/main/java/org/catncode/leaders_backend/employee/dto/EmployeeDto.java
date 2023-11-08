package org.catncode.leaders_backend.employee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.catncode.leaders_backend.account.dto.AccountDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EmployeeDto {
    @NotNull
    private AccountDto account;

    @NotNull
    private EmployeeGrade grade;

    @NotBlank
    private String locationAddress;
}
