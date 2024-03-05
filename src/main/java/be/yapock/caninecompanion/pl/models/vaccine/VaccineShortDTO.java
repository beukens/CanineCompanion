package be.yapock.caninecompanion.pl.models.vaccine;

import be.yapock.caninecompanion.dal.models.Vaccine;
import be.yapock.caninecompanion.dal.models.enums.Disease;

import java.time.LocalDate;

public record VaccineShortDTO(
        long id,
        Disease disease,
        LocalDate lastBooster,
        double frequencies
) {
    public static VaccineShortDTO fromEntity(Vaccine vaccine){
        return new VaccineShortDTO(vaccine.getId(), vaccine.getDisease(), vaccine.getLastBooster(), vaccine.getFrequencies());
    }
}
