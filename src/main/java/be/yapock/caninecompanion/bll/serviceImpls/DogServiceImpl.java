package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.DogService;
import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.pl.models.dog.DogCreateForm;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

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
}
