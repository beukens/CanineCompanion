package be.yapock.caninecompanion.pl.models.dog;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DogCreateForm(
        @NotNull @NotBlank
        String firstName,
        String lastName,
        @NotNull @NotBlank
        LocalDate dateOfBirth,
        @NotNull @NotBlank
        String sex,
        boolean isSterilized,
        long breedId,
        long ownerId
) {
}
