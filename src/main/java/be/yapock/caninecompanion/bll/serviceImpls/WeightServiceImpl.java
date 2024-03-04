package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.WeightService;
import be.yapock.caninecompanion.dal.repositories.WeightRepository;
import org.springframework.stereotype.Service;

@Service
public class WeightServiceImpl implements WeightService {
    private final WeightRepository weightRepository;

    public WeightServiceImpl(WeightRepository weightRepository) {
        this.weightRepository = weightRepository;
    }
}
