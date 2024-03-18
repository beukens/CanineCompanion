package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.ExerciceService;
import be.yapock.caninecompanion.dal.models.Exercice;
import be.yapock.caninecompanion.dal.repositories.ExerciceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ExerciceServiceImpl implements ExerciceService {
    private final ExerciceRepository exerciceRepository;

    public ExerciceServiceImpl(ExerciceRepository exerciceRepository) {
        this.exerciceRepository = exerciceRepository;
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
}
