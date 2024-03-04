package be.yapock.caninecompanion.pl.models.breed;

import be.yapock.caninecompanion.dal.models.Breed;
import be.yapock.caninecompanion.dal.models.enums.DogSize;
import be.yapock.caninecompanion.dal.models.enums.RaceGroup;

public record BreedDTO(
        String name,
        RaceGroup raceGroup,
        DogSize size,
        String temperament) {
    public static BreedDTO fromEntity(Breed breed){
        return new BreedDTO(breed.getName(), breed.getRaceGroup(),breed.getSize(), breed.getTemperament());
    }
}
