package be.yapock.caninecompanion.pl.models.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginForm(
        @NotNull @NotBlank
        String username,
        @NotNull @NotBlank
        String password
) {
}
