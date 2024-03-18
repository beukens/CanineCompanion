package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.ExerciceService;
import be.yapock.caninecompanion.dal.repositories.ExerciceRepository;
import org.springframework.stereotype.Service;

@Service
public class ExerciceServiceImpl implements ExerciceService {
    private final ExerciceRepository exerciceRepository;

    public ExerciceServiceImpl(ExerciceRepository exerciceRepository) {
        this.exerciceRepository = exerciceRepository;
    }
}
