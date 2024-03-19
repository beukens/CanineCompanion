package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.ActionPlanService;
import be.yapock.caninecompanion.dal.models.ActionPlan;
import be.yapock.caninecompanion.dal.models.Exercice;
import be.yapock.caninecompanion.dal.repositories.ActionPlanRepository;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.dal.repositories.ExerciceRepository;
import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanForm;
import be.yapock.caninecompanion.pl.models.exercice.ExerciceCreateForm;
import jakarta.persistence.EntityNotFoundException;
import be.yapock.caninecompanion.pl.models.actionPlan.ActionPlanUpdateForm;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ActionPlanServiceImpl implements ActionPlanService {
    private final ActionPlanRepository actionPlanRepository;
    private final DogRepository dogRepository;
    private final ExerciceRepository exerciceRepository;

    public ActionPlanServiceImpl(ActionPlanRepository actionPlanRepository, DogRepository dogRepository, ExerciceRepository exerciceRepository) {
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
                .endDate(LocalDate.now().plusMonths(1))
                .dog(dogRepository.findById(form.dogId()).orElseThrow(()-> new EntityNotFoundException("Chien pas trouvé")))
                .build();
        form.exercices().forEach(e -> {
            Exercice exo;
            if (exerciceRepository.existsByNameEqualsIgnoreCaseAndDescriptionEqualsIgnoreCase(e.name(),e.description())) exo = exerciceRepository.findByNameEqualsIgnoreCaseAndDescriptionEqualsIgnoreCase(e.name(), e.description());
            else exo = ExerciceCreateForm.toEntity(e);
            actionPlan.addExercices(exo);
            exerciceRepository.save(exo);
        });
        actionPlanRepository.save(actionPlan);
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
        if (form== null) throw new IllegalArgumentException("Form ne peut être null");
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

    /**
     * Retrieves a specific ActionPlan object by its ID.
     *
     * @param id The ID of the ActionPlan to retrieve.
     * @return The ActionPlan object with the specified ID.
     * @throws EntityNotFoundException if no ActionPlan with the specified ID exists.
     */
    @Override
    public ActionPlan getOneById(long id) {
        return actionPlanRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Plan d'action pas trouvé"));
    }

    /**
     * Retrieves all action plans associated with a specific dog.
     *
     * @param id the ID of the dog
     * @return a list of action plans associated with the specified dog
     */
    @Override
    public List<ActionPlan> getAllByDog(long id) {
        return actionPlanRepository.findAllByDog_Id(id);
    }

    /**
     * This method is scheduled to run daily to reset the exercises in action plans.
     * It retrieves all action plans that have an end date after today and performs the following steps for each action plan:
     *  - Check each exercise in the action plan to see if its done date is equal to the action plan's date.
     *  - If an exercise has its done date equal to the action plan's date, it is added to a list of exercises to be copied.
     *  - All exercises in the action plan, including the ones to be copied, are added to a list of all exercises for the action plan.
     *  - The list of all exercises is set as the new list of exercises for the action plan.
     * Finally, the updated action plans are saved using the action plan repository.
     *
     * This method is triggered daily at 01:00 UTC.
     *
     * Note: This method is private and cannot be directly accessed from outside the class.
     *
     * @Scheduled - Indicates that this method is scheduled to run at specified intervals.
     *      - cron: Specifies the cron expression for scheduling. In this case, it is set to "0 1 * * *", meaning it will run at 01:00 every day.
     *      - zone: Specifies the time zone to use for scheduling. In this case, it is set to "UTC".
     *
     * @throws none
     */
    @Scheduled(cron = "0 1 * * * *", zone = "UTC")
    public void resetExercices(){
        List<Exercice> exercicesToCopy = new ArrayList<>();
        List<ActionPlan> actionPlans = actionPlanRepository.findAllByEndDateAfter(LocalDate.now());
        actionPlans.forEach(e -> {
                    exercicesToCopy.addAll(e.getExercices().stream()
                            .filter(f -> f.getDoneDate().equals(e.getDate()))
                            .toList());
                    exercicesToCopy.forEach(g -> {
                        Exercice exo = Exercice.builder()
                                .name(g.getName())
                                .description(g.getDescription())
                                .build();
                        exerciceRepository.save(exo);
                        e.addExercices(exo);
                    });
                });
        actionPlanRepository.saveAll(actionPlans);
    }
}
