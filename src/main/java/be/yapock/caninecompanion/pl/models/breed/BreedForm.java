package be.yapock.caninecompanion.pl.models.breed;

import be.yapock.caninecompanion.dal.models.enums.DogSize;
import be.yapock.caninecompanion.dal.models.enums.RaceGroup;

public record BreedForm(
        String name,
        RaceGroup raceGroup,
        DogSize size,
        String temperament
) {
}
