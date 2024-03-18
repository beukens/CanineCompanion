package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.ActionPlanService;
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
