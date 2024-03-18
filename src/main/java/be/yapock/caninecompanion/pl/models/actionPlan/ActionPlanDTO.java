package be.yapock.caninecompanion.pl.models.actionPlan;

import be.yapock.caninecompanion.dal.models.ActionPlan;
import be.yapock.caninecompanion.pl.models.exercice.ExerciceShortDTO;

import java.time.LocalDate;
import java.util.List;

public record ActionPlanDTO(
        long id,
        LocalDate date,
        List<ExerciceShortDTO> exercices) {
    public static ActionPlanDTO fromEntity(ActionPlan actionPlan){
        return new ActionPlanDTO(
                actionPlan.getId(),
                actionPlan.getDate(),
                actionPlan.getExercices().stream()
                        .map(ExerciceShortDTO::fromEntity)
                        .toList()
        );
    }
}
