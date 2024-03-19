package be.yapock.caninecompanion.pl.models.exercice;

import be.yapock.caninecompanion.dal.models.Exercice;

public record ExerciceCreateForm(
        String name,
        String description
) {
    public static Exercice toEntity(ExerciceCreateForm form){
        return Exercice.builder()
                .name(form.name)
                .description(form.description)
                .isDone(false)
                .build();
    }
}
