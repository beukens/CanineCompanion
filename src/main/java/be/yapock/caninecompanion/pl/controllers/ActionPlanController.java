package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.ActionPlanService;
import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actionplan")
public class ActionPlanController {
    private final ActionPlanService actionPlanService;

    public ActionPlanController(ActionPlanService actionPlanService) {
        this.actionPlanService = actionPlanService;
    }

    /**
     * Creates a new action plan based on the provided form.
     *
     * @param form The data needed to create the action plan.
     */
    @PostMapping("/")
    public void createActionPlan(@RequestBody ActionPlanForm form) {
        actionPlanService.create(form);
    }
}
