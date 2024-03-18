package be.yapock.caninecompanion.pl.models.actionPlan;

import java.time.LocalDate;
import java.util.List;

public record ActionPlanForm(
        LocalDate date,
        List<Long> exercicesId
) {
}
