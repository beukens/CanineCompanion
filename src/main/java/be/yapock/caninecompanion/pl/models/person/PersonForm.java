package be.yapock.caninecompanion.pl.models.person;

import be.yapock.caninecompanion.pl.models.validation.constraints.ValidEmail;
import be.yapock.caninecompanion.pl.models.validation.constraints.ValidPhoneNumber;
import jakarta.validation.constraints.*;

public record PersonForm(
        @NotBlank @NotNull
        String firstName,
        @NotBlank @NotNull
        String lastName,
        @NotBlank @NotNull
        @Email
        String mail,
        @NotBlank @NotNull
        @ValidPhoneNumber
        String phoneNumber,
        @NotBlank @NotNull
        String gender,
        @NotNull @NotBlank
        String street,
        @NotNull @NotBlank @Min(1)
        int number,
        @NotNull @NotBlank
        String box,
        @NotNull @NotBlank @Min(1000) @Max(99999)
        int zip,
        @NotNull @NotBlank
        String city,
        @NotNull @NotBlank
        String country
) {
}
