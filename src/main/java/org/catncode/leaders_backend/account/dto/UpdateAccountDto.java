package org.catncode.leaders_backend.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountDto {
    @NotBlank
    private String login;

    @NotBlank
    private String fullName;

    @NotNull
    private AccountRole role;
}
