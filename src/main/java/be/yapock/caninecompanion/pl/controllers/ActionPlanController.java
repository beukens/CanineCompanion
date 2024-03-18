package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.ActionPlanService;
import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanUpdateForm;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Updates an action plan with the given ID using the provided form.
     *
     * @param id   The ID of the action plan to update.
     * @param form The form containing the updated action plan information.
     */
    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody ActionPlanUpdateForm form){
        actionPlanService.update(id, form);
    }
}
