package be.yapock.caninecompanion.pl.models.dog;

import be.yapock.caninecompanion.dal.models.Dog;

public record DogShortDTO(
        String firstName
) {
    public static DogShortDTO fromEntity(Dog dog){
        return new DogShortDTO(dog.getFirstName());
    }
}
