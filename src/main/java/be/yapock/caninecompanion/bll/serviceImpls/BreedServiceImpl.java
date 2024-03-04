package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.BreedService;
import be.yapock.caninecompanion.dal.repositories.BreedRepository;
import org.springframework.stereotype.Service;

@Service
public class BreedServiceImpl implements BreedService {
    private final BreedRepository breedRepository;

    public BreedServiceImpl(BreedRepository breedRepository) {
        this.breedRepository = breedRepository;
    }
}
