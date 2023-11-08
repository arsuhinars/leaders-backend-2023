package org.catncode.leaders_backend.account.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AccountDto {
    @NotNull
    @Min(1)
    private Integer id;

    @NotBlank
    private String login;

    @NotBlank
    private String fullName;

    @NotNull
    private AccountRole role;
}
