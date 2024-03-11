package be.yapock.caninecompanion.pl.models.diagnostic;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DiagnosticForm(
        @NotBlank @NotNull @Min(1) @Max(5)
        int submissivePosition,
        @NotBlank @NotNull @Min(1) @Max(5)
        int withFamiliarHuman,
        @NotBlank @NotNull @Min(1) @Max(5)
        int withStranger,
        @NotBlank @NotNull @Min(1) @Max(5)
        int withDogs,
        @NotBlank @NotNull @Min(1) @Max(5)
        int withOtherAnimals,
        @NotBlank @NotNull @Min(1) @Max(5)
        int stayingAlone,
        @NotBlank @NotNull @Min(1) @Max(5)
        int affrayed,
        @NotBlank @NotNull @Min(1) @Max(5)
        int contactWHumans,
        @NotBlank @NotNull @Min(1) @Max(5)
        int contactWAnimals,
        @NotBlank @NotNull @Min(1) @Max(5)
        int adaptability,
        @NotBlank @NotNull @Min(1) @Max(5)
        int attachement,
        @NotBlank @NotNull @Min(1) @Max(5)
        int separation,
        @NotBlank @NotNull @Min(1) @Max(5)
        int restPlace,
        @NotBlank @NotNull @Min(1) @Max(5)
        int exploration,
        @NotBlank @NotNull @Min(1) @Max(5)
        int tenderness,
        @NotBlank @NotNull @Min(1) @Max(5)
        int vocalize,
        @NotBlank @NotNull @Min(1) @Max(5)
        int jumpOnPeople,
        @NotBlank @NotNull @Min(1) @Max(5)
        int destruct,
        @NotBlank @NotNull @Min(1) @Max(5)
        int scratchesBruises,
        @NotBlank @NotNull @Min(1) @Max(5)
        int excitation,
        long dogId


) {
}
