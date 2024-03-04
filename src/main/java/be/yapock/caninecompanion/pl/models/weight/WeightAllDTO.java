package be.yapock.caninecompanion.pl.models.weight;

import be.yapock.caninecompanion.dal.models.Weight;

import java.time.LocalDate;

public record WeightAllDTO(
        LocalDate date,
        double weight
) {
    public static WeightAllDTO fromEntity(Weight weight){
        return new WeightAllDTO(weight.getDate(), weight.getWeight());
    }
}
