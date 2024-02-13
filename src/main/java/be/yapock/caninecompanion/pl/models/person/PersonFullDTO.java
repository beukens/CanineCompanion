package be.yapock.caninecompanion.pl.models.person;

import be.yapock.caninecompanion.dal.models.Person;

public record PersonFullDTO(
        long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        char gender
) {
    public static PersonFullDTO fromEntity(Person person){
        return new PersonFullDTO(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getEmail(),
                person.getPhoneNumber(),
                person.getGender()
        );
    }
}
