package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.ActionPlanService;
import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanUpdateForm;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Deletes an action plan based on the provided ID.
     *
     * @param id The ID of the action plan to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteActionPlan(@PathVariable long id) {
        actionPlanService.delete(id);
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

    /**
     * Retrieves a list of ActionPlanDTO objects representing all action plans associated with a dog.
     *
     * @param id The ID of the dog.
     * @return A ResponseEntity containing a list of ActionPlanDTO objects.
     */
    @GetMapping("/dog/{id}")
    public ResponseEntity<List<ActionPlanDTO>> getAllByDog(@PathVariable long id) {
        return ResponseEntity.ok(actionPlanService.getAllByDog(id).stream()
                .map(ActionPlanDTO::fromEntity)
                .collect(Collectors.toList()));
    }
}
