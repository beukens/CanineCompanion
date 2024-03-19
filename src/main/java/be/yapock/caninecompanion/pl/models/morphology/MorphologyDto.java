package be.yapock.caninecompanion.pl.models.morphology;

import be.yapock.caninecompanion.dal.models.Morphology;
import be.yapock.caninecompanion.pl.models.breed.BreedDTO;

import java.util.List;

public record MorphologyDto(
        String coat,
        double height,
        double chestPerimeter,
        double frontBackProportion,
        String headMorphology,
        String notes,
        List<BreedDTO> breeds
) {
    public static MorphologyDto fromEntity(Morphology morphology){
        if (morphology != null)
            return new MorphologyDto(
                morphology.getCoat(),
                morphology.getHeight(),
                morphology.getChestPerimeter(),
                morphology.getFrontBackProportion(),
                morphology.getHeadMorphology(),
                morphology.getNotes(),
                morphology.getBreeds().stream()
                        .map(BreedDTO::fromEntity)
                        .toList()
            );
        else return null;
    }
}
