package be.yapock.caninecompanion.pl.models.exercice;

public record ExerciceCreateForm(
        String name,
        String description,
        double frequencies
) {
}
