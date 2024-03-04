package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.WeightService;
import be.yapock.caninecompanion.dal.models.Weight;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.dal.models.Weight;
import be.yapock.caninecompanion.dal.repositories.WeightRepository;
import be.yapock.caninecompanion.pl.models.weight.WeightForm;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WeightServiceImpl implements WeightService {
    private final WeightRepository weightRepository;
    private final DogRepository dogRepository;

    public WeightServiceImpl(WeightRepository weightRepository, DogRepository dogRepository) {
        this.weightRepository = weightRepository;
        this.dogRepository = dogRepository;
    }

    /**
     * Creates a new weight entry for a dog.
     *
     * @param form the weight form containing the dog ID and weight value
     * @throws EntityNotFoundException if the dog with the specified ID is not found
     */
    @Override
    public void create(WeightForm form){
        if (form==null) throw new IllegalArgumentException("le formulaire ne peut être null");
        Weight weight = Weight.builder()
                .date(LocalDate.now())
                .dog(dogRepository.findById(form.dogId()).orElseThrow(()->new EntityNotFoundException("Chien pas trouvé")))
                .weight(form.wheight())
                .build();
        weightRepository.save(weight);
    }
    /**
     * Retrieves the weight information for a given dog ID.
     *
     * @param id The ID of the dog.
     * @return The weight information for the dog.
     * @throws EntityNotFoundException if the dog is not found.
     */
    @Override
    public Weight getOne(long id) {
        return weightRepository.findLastByDog_Id(id).orElseThrow(()->new EntityNotFoundException("Chien pas trouvé"));
    }
}
