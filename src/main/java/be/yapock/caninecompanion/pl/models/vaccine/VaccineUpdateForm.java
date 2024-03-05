package be.yapock.caninecompanion.pl.models.vaccine;

import java.time.LocalDate;

public record VaccineUpdateForm(
        LocalDate date
) {
}
