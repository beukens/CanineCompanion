package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.DogService;
import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.pl.models.dog.DogCreateForm;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * Find all dogs owned by the given owner.
     *
     * @param id the ID of the owner
     * @return a list of Dog objects owned by the given owner
     */
    @Override
    public List<Dog> findAllByOwner(long id) {
        return dogRepository.findAllByOwner(id);
    }
}
