package be.yapock.caninecompanion.pl.models.exercice;

import be.yapock.caninecompanion.dal.models.Exercice;

import java.time.LocalDate;
import java.util.List;

public record ExerciceFullDTO(
        long id,
        String name,
        boolean isDone,
        String description
) {
    public static ExerciceFullDTO fromEntity(Exercice exercice){
        return new ExerciceFullDTO(
                exercice.getId(),
                exercice.getName(),
                exercice.isDone(),
                exercice.getDescription()
        );
    }
}
