package be.yapock.caninecompanion.pl.models.person;

import be.yapock.caninecompanion.dal.models.Person;

public record PersonShortDTO(
        long id,
        String firstName,
        String lastName,
        String phoneNumber
) {
    public static PersonShortDTO fromEntity(Person person){
        return new PersonShortDTO(person.getId(), person.getFirstName(), person.getLastName(), person.getPhoneNumber());
    }
}
