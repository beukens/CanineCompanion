package be.yapock.caninecompanion.pl.models.dog;

import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.pl.models.morphology.MorphologyDto;

import java.time.LocalDate;

public record DogFullDTO(
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String sexe,
        boolean isSterilized,
        long breedId,
        MorphologyDto morphology
) {
    public static DogFullDTO fromEntity(Dog dog){
        return new DogFullDTO(
                dog.getFirstName(),
                dog.getLastName(),
                dog.getDateOfBirth(),
                dog.getSex(),
                dog.isSterilized(),
                dog.getBreed().getId(),
                MorphologyDto.fromEntity(dog.getMorphology())
        );
    }
}
