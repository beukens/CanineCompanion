package be.yapock.caninecompanion.pl.models.dog;

import java.time.LocalDate;

public record DogCreateForm(
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String sex,
        boolean isSterilized
) {
}
