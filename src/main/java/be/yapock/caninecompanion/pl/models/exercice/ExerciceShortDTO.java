package be.yapock.caninecompanion.pl.models.exercice;

import be.yapock.caninecompanion.dal.models.Exercice;

public record ExerciceShortDTO(
        long id,
        String name,
        double frequencies,
        boolean isDone
) {
    public static ExerciceShortDTO fromEntity(Exercice exercice){
        return new ExerciceShortDTO(
                exercice.getId(),
                exercice.getName(),
                exercice.getFrequencies(),
                exercice.isDone()
        );
    }
}
