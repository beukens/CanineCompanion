package be.yapock.caninecompanion.pl.models.address;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressForm(
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
        String country,
        @NotNull @NotBlank
        long personId
) {
}
