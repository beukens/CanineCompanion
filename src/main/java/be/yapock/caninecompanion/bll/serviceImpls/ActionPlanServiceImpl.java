package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.ActionPlanService;
import be.yapock.caninecompanion.dal.models.ActionPlan;
import be.yapock.caninecompanion.dal.repositories.ActionPlanRepository;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.dal.repositories.ExerciceRepository;
import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanForm;
import jakarta.persistence.EntityNotFoundException;
import be.yapock.caninecompanion.dal.repositories.ExerciceRepository;
import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanUpdateForm;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ActionPlanServiceImpl implements ActionPlanService {
    private final ActionPlanRepository actionPlanRepository;
    private final DogRepository dogRepository;
    private final ExerciceRepository exerciceRepository;
    private final ExerciceRepository exerciceRepository;

    public ActionPlanServiceImpl(ActionPlanRepository actionPlanRepository, DogRepository dogRepository, ExerciceRepository exerciceRepository) {
    public ActionPlanServiceImpl(ActionPlanRepository actionPlanRepository, ExerciceRepository exerciceRepository) {
        this.actionPlanRepository = actionPlanRepository;
        this.dogRepository = dogRepository;
        this.exerciceRepository = exerciceRepository;
    }

    /**
     * Creates a new action plan based on the given form.
     *
     * @param form the action plan form containing the necessary data for creating the action plan
     * @throws IllegalArgumentException if the form is null
     * @throws EntityNotFoundException if the dog is not found in the dog repository
     */
    @Override
    public void create(ActionPlanForm form) {
        if (form==null) throw new IllegalArgumentException("Form ne peut être null");
        ActionPlan actionPlan = ActionPlan.builder()
                .date(LocalDate.now())
                .dog(dogRepository.findById(form.dogId()).orElseThrow(()-> new EntityNotFoundException("Chien pas trouvé")))
                .exercices(exerciceRepository.findAllById(form.exercicesId()))
                .build();
        actionPlanRepository.save(actionPlan);
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

    /**
     * Deletes an ActionPlan entity by its ID.
     *
     * @param id the ID of the ActionPlan entity to be deleted
     */
    @Override
    public void delete(long id) {
        actionPlanRepository.deleteById(id);
    }
}
