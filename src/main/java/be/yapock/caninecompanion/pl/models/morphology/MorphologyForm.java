package be.yapock.caninecompanion.pl.models.morphology;

import java.util.List;

public record MorphologyForm(
        String coat,
        double height,
        double chestPerimeter,
        double frontBackProportion,
        String headMorphology,
        String notes,
        List<Long> breedId,
        long dogId
) {
}
