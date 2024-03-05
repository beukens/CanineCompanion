package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.VaccineService;
import be.yapock.caninecompanion.dal.repositories.VaccineRepository;
import org.springframework.stereotype.Service;

@Service
public class VaccineServiceImpl implements VaccineService {
    private final VaccineRepository vaccineRepository;

    public VaccineServiceImpl(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    @Override
    public void delete(long id) {
        vaccineRepository.deleteById(id);
    }
}
