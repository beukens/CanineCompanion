package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.DogService;
import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.dal.repositories.SpecificationBuilder;
import be.yapock.caninecompanion.pl.models.dog.DogCreateForm;
import be.yapock.caninecompanion.pl.models.dog.DogSearchForm;
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
     * Searches for dogs based on the provided search form.
     *
     * @param form The search form containing the criteria for the search.
     * @return A list of dogs that match the search criteria.
     */
    @Override
    public List<Dog> search(DogSearchForm form) {
        return dogRepository.findAll(SpecificationBuilder.specificationBuilder(form));
    }
}
