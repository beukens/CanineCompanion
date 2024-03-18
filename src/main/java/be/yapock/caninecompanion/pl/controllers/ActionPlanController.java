package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.ActionPlanService;
import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
     * Retrieves an Action Plan by its ID.
     *
     * @param id the ID of the Action Plan
     * @return a ResponseEntity containing the ActionPlanDTO representation of the retrieved Action Plan
     */
    @GetMapping("/{id}")
    public ResponseEntity<ActionPlanDTO> getOneById(@PathVariable long id) {
        return ResponseEntity.ok(ActionPlanDTO.fromEntity(actionPlanService.getOneById(id)));
    }
}
