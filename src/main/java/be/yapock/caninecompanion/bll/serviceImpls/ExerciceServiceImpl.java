package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.ExerciceService;
import be.yapock.caninecompanion.dal.models.Exercice;
import be.yapock.caninecompanion.dal.repositories.ExerciceRepository;
import be.yapock.caninecompanion.pl.models.exercice.ExerciceCheckForm;
import be.yapock.caninecompanion.pl.models.exercice.ExerciceCreateForm;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ExerciceServiceImpl implements ExerciceService {
    private final ExerciceRepository exerciceRepository;

    public ExerciceServiceImpl(ExerciceRepository exerciceRepository) {
        this.exerciceRepository = exerciceRepository;
    }

    /**
     * Creates a new exercise using the provided form.
     *
     * @param form The form containing the exercise information. Must not be null.
     * @throws IllegalArgumentException If the form is null.
     */
    @Override
    public void create(ExerciceCreateForm form) {
        if (form==null) throw new IllegalArgumentException("Form ne peut être null");
        Exercice exercice = Exercice.builder()
                .name(form.name())
                .description(form.description())
                .isDone(false)
                .build();
        exerciceRepository.save(exercice);
    }

    /**
     * Retrieves an Exercice by its id.
     *
     * @param id the id of the Exercice to retrieve
     * @return the Exercice with the specified id
     * @throws EntityNotFoundException if no Exercice is found with the specified id
     */
    @Override
    public Exercice getOneById(long id) {
        return exerciceRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Exercice pas trouvé"));
    }

    /**
     * Deletes an Exercice by its id.
     *
     * @param id the id of the Exercice to delete
     * @throws IllegalArgumentException if the id is not valid
     */
    @Override
    public void delete(long id) {
        exerciceRepository.deleteById(id);
    }

    /**
     * Updates the exercise with the specified id using the provided form.
     *
     * @param id   The id of the exercise to update.
     * @param form The form containing the exercise information. Must not be null.
     * @throws IllegalArgumentException      If the form is null.
     * @throws EntityNotFoundException        If no exercise is found with the specified id.
     */
    @Override
    public void update(long id, ExerciceCheckForm form) {
        if (form == null) throw new IllegalArgumentException("Form ne peut être null");
        Exercice exercice = exerciceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Exercice pas trouvé"));
        exercice.setDone(form.isDone());
        exerciceRepository.save(exercice);
    }
}
