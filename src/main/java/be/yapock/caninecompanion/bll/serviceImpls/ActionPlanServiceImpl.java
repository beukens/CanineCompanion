package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.ActionPlanService;
import be.yapock.caninecompanion.dal.models.ActionPlan;
import be.yapock.caninecompanion.dal.repositories.ActionPlanRepository;
import be.yapock.caninecompanion.dal.repositories.ExerciceRepository;
import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanUpdateForm;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ActionPlanServiceImpl implements ActionPlanService {
    private final ActionPlanRepository actionPlanRepository;
    private final ExerciceRepository exerciceRepository;

    public ActionPlanServiceImpl(ActionPlanRepository actionPlanRepository, ExerciceRepository exerciceRepository) {
        this.actionPlanRepository = actionPlanRepository;
        this.exerciceRepository = exerciceRepository;
    }

    /**
     * Updates an ActionPlan with the specified id using the provided ActionPlanUpdateForm.
     *
     * @param id   The id of the ActionPlan to update.
     * @param form The ActionPlanUpdateForm containing the updated data.
     * @throws IllegalArgumentException   if the form is null.
     * @throws EntityNotFoundException     if the ActionPlan with the specified id is not found.
     */
    @Override
    public void update(long id, ActionPlanUpdateForm form) {
        if (form== null) throw new IllegalArgumentException("Form ne peut être vide");
        ActionPlan actionPlan = actionPlanRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Plan d'action pas trouvé"));
        actionPlan.setExercices(exerciceRepository.findAllById(form.exercicesId()));
        actionPlanRepository.save(actionPlan);
    }
}
