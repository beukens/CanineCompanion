package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.BreedService;
import be.yapock.caninecompanion.dal.models.Breed;
import be.yapock.caninecompanion.dal.repositories.BreedRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreedServiceImpl implements BreedService {
    private final BreedRepository breedRepository;

    public BreedServiceImpl(BreedRepository breedRepository) {
        this.breedRepository = breedRepository;
    }

    /**
     * Retrieves a Breed object from the breed repository based on the provided id.
     *
     * @param id the id of the breed to retrieve
     * @return the Breed object with the specified id, or null if no such breed exists
     */
    @Override
    public Breed getOne(long id) {
        return breedRepository.getOne(id);
    }


    /**
     * Retrieves all Breed objects from the breed repository.
     *
     * @return a List of all Breed objects in the repository
     */
    @Override
    public List<Breed> getAll() {
        return breedRepository.findAll();
    }
}
