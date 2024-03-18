package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanForm;

import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanForm;
import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanUpdateForm;

import be.yapock.caninecompanion.dal.models.ActionPlan;

import java.util.List;

public interface ActionPlanService {
    void create(ActionPlanForm form);
    void update(long id, ActionPlanUpdateForm form);
    void delete(long id);
    ActionPlan getOneById(long id);
    List<ActionPlan> getAllByDog(long id);
}
