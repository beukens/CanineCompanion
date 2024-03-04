package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.DogService;
import be.yapock.caninecompanion.dal.models.Dog;
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
     * Updates the information of a dog with the provided form and id.
     *
     * @param form The form containing the updated information for the dog. Cannot be null.
     * @param id   The id of the dog to be updated.
     * @throws IllegalArgumentException If the form is null or if the dog with the provided id does not exist.
     */
    @Override
    public void update(DogCreateForm form, long id) {
        if (form==null) throw new IllegalArgumentException("Le formulaire ne peut être Vide");
        Dog dog = dogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Le chien avec l'ID " + id + " n'existe pas"));
        dog.setFirstName(form.firstName());
        dog.setLastName(form.lastName());
        dog.setDateOfBirth(form.dateOfBirth());
        dog.setSex(form.sex());
        dog.setSterilized(form.isSterilized());
        dogRepository.save(dog);
    }
}
