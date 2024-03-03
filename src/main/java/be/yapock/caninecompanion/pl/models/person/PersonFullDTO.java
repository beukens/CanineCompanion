package be.yapock.caninecompanion.pl.models.person;

import be.yapock.caninecompanion.dal.models.Person;

public record PersonFullDTO(
        long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String gender,
        int number,
        String box,
        String street,
        int zip,
        String city,
        String country
) {
    public static PersonFullDTO fromEntity(Person person){
        return new PersonFullDTO(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getEmail(),
                person.getPhoneNumber(),
                person.getGender(),
                person.getNumber(),
                person.getBox(),
                person.getStreet(),
                person.getZip(),
                person.getCity(),
                person.getCountry()
        );
    }
}
