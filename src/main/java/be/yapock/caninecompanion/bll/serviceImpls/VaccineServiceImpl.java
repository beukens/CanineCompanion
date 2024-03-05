package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.VaccineService;
import be.yapock.caninecompanion.dal.models.Vaccine;
import be.yapock.caninecompanion.dal.repositories.VaccineRepository;
import be.yapock.caninecompanion.pl.models.vaccine.VaccineUpdateForm;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class VaccineServiceImpl implements VaccineService {
    private final VaccineRepository vaccineRepository;

    public VaccineServiceImpl(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    @Override
    public void update(VaccineUpdateForm form, long id) {
        if (form==null) throw new IllegalArgumentException("Le formulaire ne peut être null");
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vaccin pas trouvé"));
        vaccine.setLastBooster(form.date());
        vaccineRepository.save(vaccine);
    }
}
