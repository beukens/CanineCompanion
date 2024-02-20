package be.yapock.caninecompanion.pl.models.person;

import be.yapock.caninecompanion.pl.models.validation.constraints.ValidEmail;
import be.yapock.caninecompanion.pl.models.validation.constraints.ValidPhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
        @NotBlank @NotNull
        long adressId
) {
}
