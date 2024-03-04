package be.yapock.caninecompanion.pl.models.dog;

import be.yapock.caninecompanion.dal.models.Dog;

public record DogShortDTO(
        String firstName,
        long ownerId,
        long breedId
) {
    public static DogShortDTO fromEntity(Dog dog){
        return new DogShortDTO(dog.getFirstName(), dog.getOwner().getId(), dog.getBreed().getId());
    }
}
