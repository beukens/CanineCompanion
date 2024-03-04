package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.DogService;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.pl.models.dog.DogCreateForm;
import org.springframework.stereotype.Service;

@Service
public class DogServiceImpl implements DogService {
    private final DogRepository dogRepository;

    public DogServiceImpl(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public void create(DogCreateForm form) {

    }

    /**
     * Deletes a dog with the specified ID.
     *
     * @param id the ID of the dog to delete
     */
    @Override
    public void delete(long id) {
        dogRepository.deleteById(id);
    }
}
