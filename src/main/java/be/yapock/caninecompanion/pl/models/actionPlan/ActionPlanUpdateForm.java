package be.yapock.caninecompanion.pl.models.actionPlan;

import java.util.List;

public record ActionPlanUpdateForm(
        List<Long> exercicesId
) {
}
