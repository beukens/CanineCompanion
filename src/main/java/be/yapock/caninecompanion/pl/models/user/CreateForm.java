package be.yapock.caninecompanion.pl.models.user;

import jakarta.validation.constraints.Pattern;

public record CreateForm(
        @Pattern.List({
                @Pattern(regexp = ".*[A-Z].*", message = "Le mot de passe doit contenir au moins une majuscule"),
                @Pattern(regexp = ".*\\d.*", message = "Le mot de passe doit contenir au moins un chiffre"),
                @Pattern(regexp = ".*[!@#$%^&*()\\-_=+{};:,<.>/?\\[\\]\\\\].*", message = "Le mot de passe doit contenir au moins un caractère spécial")
        })
        String password,
        @Pattern.List({
                @Pattern(regexp = ".*[A-Z].*", message = "Le mot de passe doit contenir au moins une majuscule"),
                @Pattern(regexp = ".*\\d.*", message = "Le mot de passe doit contenir au moins un chiffre"),
                @Pattern(regexp = ".*[!@#$%^&*()\\-_=+{};:,<.>/?\\[\\]\\\\].*", message = "Le mot de passe doit contenir au moins un caractère spécial")
        })
        String confirmedPassword
) {
}
