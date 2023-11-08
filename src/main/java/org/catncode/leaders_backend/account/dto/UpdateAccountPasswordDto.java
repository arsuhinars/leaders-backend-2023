package org.catncode.leaders_backend.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountPasswordDto {
    @NotBlank
    @Size(min = 8)
    private String newPassword;
}
