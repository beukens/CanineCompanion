package be.yapock.caninecompanion.pl.models.dog;

import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.pl.models.breed.BreedDTO;

import java.time.LocalDate;

public record DogFullDTO(
        long id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String sex,
        boolean isSterilized,
        BreedDTO breed
) {
    public static DogFullDTO fromEntity(Dog dog){
        return new DogFullDTO(
                dog.getId(),
                dog.getFirstName(),
                dog.getLastName(),
                dog.getDateOfBirth(),
                dog.getSex(),
                dog.isSterilized(),
                BreedDTO.fromEntity(dog.getBreed())
        );
    }
}
