package be.yapock.caninecompanion.pl.models.weight;

import be.yapock.caninecompanion.dal.models.Weight;

public record WeightOneDto(
        double weight
) {
    public static WeightOneDto fromEntity(Weight weight){
        return new WeightOneDto(weight.getWeight());
    }
}
