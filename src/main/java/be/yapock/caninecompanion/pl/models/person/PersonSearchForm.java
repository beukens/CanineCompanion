package be.yapock.caninecompanion.pl.models.person;

public record PersonSearchForm(
        String firstName,
        String lastName,
        String phoneNumber
) {
}
