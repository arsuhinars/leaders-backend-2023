package org.catncode.leaders_backend.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountDto {
    @NotBlank
    private String login;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    private String fullName;

    @NotNull
    private AccountRole role;
}
