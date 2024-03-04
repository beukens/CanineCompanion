package be.yapock.caninecompanion.pl.models.dog;

import be.yapock.caninecompanion.dal.models.Dog;

import java.time.LocalDate;

public record DogFullDTO(
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String sexe,
        boolean isSterilized
) {
    public static DogFullDTO fromEntity(Dog dog){
        return new DogFullDTO(
                dog.getFirstName(),
                dog.getLastName(),
                dog.getDateOfBirth(),
                dog.getSex(),
                dog.isSterilized()
        );
    }
}
