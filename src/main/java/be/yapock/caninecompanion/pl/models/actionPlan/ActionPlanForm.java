package be.yapock.caninecompanion.pl.models.actionPlan;

import be.yapock.caninecompanion.pl.models.exercice.ExerciceCreateForm;

import java.time.LocalDate;
import java.util.List;

public record ActionPlanForm(
        long dogId,
        List<ExerciceCreateForm> exercices
) {
}
