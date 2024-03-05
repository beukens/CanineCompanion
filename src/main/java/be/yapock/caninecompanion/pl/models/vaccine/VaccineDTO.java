package be.yapock.caninecompanion.pl.models.vaccine;

import be.yapock.caninecompanion.dal.models.Vaccine;
import be.yapock.caninecompanion.dal.models.enums.Disease;

import java.time.LocalDate;

public record VaccineDTO(
        Disease disease,
        LocalDate lastBooster,
        double frequencies) {
    public static VaccineDTO fromEntity(Vaccine vaccine){
        return new VaccineDTO(vaccine.getDisease(), vaccine.getLastBooster(), vaccine.getFrequencies());
    }
}
