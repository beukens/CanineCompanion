package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanForm;

import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanForm;
import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanUpdateForm;

public interface ActionPlanService {
    void create(ActionPlanForm form);
    void update(long id, ActionPlanUpdateForm form);
    void delete(long id);
}
