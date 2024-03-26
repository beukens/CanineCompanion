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
        List<Exercice> exercices = new ArrayList<>();
        if (form==null) throw new IllegalArgumentException("Form ne peut être null");
        ActionPlan actionPlan = ActionPlan.builder()
                .date(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(1))
                .dog(dogRepository.findById(form.dogId()).orElseThrow(()-> new EntityNotFoundException("Chien pas trouvé")))
                .build();
        form.exercices().forEach(e -> {
            Exercice exo = ExerciceCreateForm.toEntity(e);
            exo.setDate(LocalDate.now());
            exercices.add(exo);
            exerciceRepository.save(exo);
        });
        actionPlan.setExercices(exercices);
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
     * Reset the exercises by copying the exercises from the active action plans and saving them as new exercises with the current date.
     * This method is scheduled to run daily at 1 AM UTC.
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void resetExercices(){
        List<Exercice> exercicesToCopy = new ArrayList<>();
        List<ActionPlan> actionPlans = actionPlanRepository.findAllByEndDateAfter(LocalDate.now());
        actionPlans.forEach(e -> {
                    exercicesToCopy.addAll(e.getExercices().stream()
                            .filter(f -> f.getDate().equals(e.getDate()))
                            .toList());
                    exercicesToCopy.forEach(g -> {
                        Exercice exo = Exercice.builder()
                                .name(g.getName())
                                .description(g.getDescription())
                                .date(LocalDate.now())
                                .build();
                        exerciceRepository.save(exo);
                        e.addExercices(exo);
                    });
                });
        actionPlanRepository.saveAll(actionPlans);
    }

    /**
     * Retrieves the last action plan associated with a specific dog.
     *
     * @param id the ID of the dog
     * @return the last action plan associated with the specified dog
     * @throws EntityNotFoundException if no action plan is found for the specified dog
     */
    @Override
    public ActionPlan getLastByDogId(long id) {
        return actionPlanRepository.findFirstByDog_IdOrderByDateDesc(id).orElseThrow(()->new EntityNotFoundException("Plan d'action pas trouvé"));
    }
}
