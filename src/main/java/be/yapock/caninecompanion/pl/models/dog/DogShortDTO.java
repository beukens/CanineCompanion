package be.yapock.caninecompanion.pl.models.dog;

import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.pl.models.breed.BreedDTO;
import be.yapock.caninecompanion.pl.models.person.PersonShortDTO;

public record DogShortDTO(
        long id,
        String firstName,
        PersonShortDTO owner,
        BreedDTO breed
) {
    public static DogShortDTO fromEntity(Dog dog){
        return new DogShortDTO(dog.getId(), dog.getFirstName(), PersonShortDTO.fromEntity(dog.getOwner()), BreedDTO.fromEntity(dog.getBreed()));
    }
}
