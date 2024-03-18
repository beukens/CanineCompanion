package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanForm;
import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanUpdateForm;

public interface ActionPlanService {
    void update(long id, ActionPlanUpdateForm form);
}
