package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.DogService;
import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.dal.repositories.SpecificationBuilder;
import be.yapock.caninecompanion.pl.models.dog.DogCreateForm;
import jakarta.persistence.EntityNotFoundException;
import be.yapock.caninecompanion.pl.models.dog.DogSearchForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogServiceImpl implements DogService {
    private final DogRepository dogRepository;

    public DogServiceImpl(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    /**
     * Creates a new dog entity based on the provided form and saves it to the dog repository.
     *
     * @param form The object representing the form data for creating a dog entity.
     * @throws IllegalArgumentException if the form is null.
     */
    @Override
    public void create(DogCreateForm form) {
        if (form==null) throw new IllegalArgumentException("Le formulaire ne peut être vide");
        Dog dog = Dog.builder()
                .firstName(form.firstName())
                .lastName(form.lastName())
                .isSterilized(form.isSterilized())
                .dateOfBirth(form.dateOfBirth())
                .sex(form.sex())
                .build();
        dogRepository.save(dog);
    }

    /**
     * Retrieves a dog by its ID.
     *
     * @param id the ID of the dog to retrieve
     * @return the dog with the specified ID
     * @throws EntityNotFoundException if the dog is not found
     */
    @Override
    public Dog getDogById(Long id) {
        return dogRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Chien pas trouvé"));
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
