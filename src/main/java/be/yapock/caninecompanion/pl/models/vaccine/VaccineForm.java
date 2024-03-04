package be.yapock.caninecompanion.pl.models.vaccine;

import be.yapock.caninecompanion.dal.models.enums.Disease;

import java.time.LocalDate;

public record VaccineForm(
        Disease disease,
        LocalDate dateBooster,
        Long dogId
) {
}
