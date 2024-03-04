package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.WeightService;
import be.yapock.caninecompanion.dal.models.Weight;
import be.yapock.caninecompanion.dal.repositories.WeightRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class WeightServiceImpl implements WeightService {
    private final WeightRepository weightRepository;

    public WeightServiceImpl(WeightRepository weightRepository) {
        this.weightRepository = weightRepository;
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
