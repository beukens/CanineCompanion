package be.yapock.caninecompanion.pl.models.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PasswordResetRequestForm(
        @NotBlank @NotNull
        String login,
        @Email @NotNull @NotBlank
        String email
) {
}
