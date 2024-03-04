package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.BreedService;
import be.yapock.caninecompanion.dal.models.Breed;
import be.yapock.caninecompanion.dal.repositories.BreedRepository;
import jakarta.persistence.EntityNotFoundException;
import be.yapock.caninecompanion.pl.models.breed.BreedForm;
import org.springframework.stereotype.Service;

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
        return breedRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Race pas trouvée"));
    }

    /**
     * Creates a new Breed object based on the provided form and saves it to the breed repository.
     *
     * @param form the BreedForm object representing the data of the new breed to create
     * @throws IllegalArgumentException if the form is null
     */
    @Override
    public void create(BreedForm form) {
        if (form==null) throw new IllegalArgumentException("le Formulaire ne peut être vide");
        Breed breed = Breed.builder()
                .name(form.name())
                .raceGroup(form.raceGroup())
                .size(form.size())
                .temperament(form.temperament())
                .build();
        breedRepository.save(breed);
    }
}
