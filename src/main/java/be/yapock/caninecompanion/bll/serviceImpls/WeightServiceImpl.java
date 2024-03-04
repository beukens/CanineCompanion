package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.WeightService;
import be.yapock.caninecompanion.dal.models.Weight;
import be.yapock.caninecompanion.dal.repositories.WeightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeightServiceImpl implements WeightService {
    private final WeightRepository weightRepository;

    public WeightServiceImpl(WeightRepository weightRepository) {
        this.weightRepository = weightRepository;
    }

    /**
     * Retrieves all weight records for a specific dog.
     *
     * @param id the ID of the dog
     * @return a list of weight records for the specified dog
     */
    @Override
    public List<Weight> getAll(long id) {
        return weightRepository.findAllByDog_Id(id);
    }
}
