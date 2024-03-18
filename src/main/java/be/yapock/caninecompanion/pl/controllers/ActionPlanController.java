package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.ActionPlanService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * Deletes an action plan based on the provided ID.
     *
     * @param id The ID of the action plan to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteActionPlan(@PathVariable long id) {
        actionPlanService.delete(id);
    }
}
