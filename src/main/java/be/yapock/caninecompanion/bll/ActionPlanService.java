package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.dal.models.ActionPlan;

public interface ActionPlanService {
    ActionPlan getOneById(long id);
}
