package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.VaccineService;
import be.yapock.caninecompanion.dal.models.Vaccine;
import be.yapock.caninecompanion.dal.models.enums.Disease;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.dal.models.Vaccine;
import be.yapock.caninecompanion.dal.repositories.VaccineRepository;
import be.yapock.caninecompanion.pl.models.vaccine.VaccineForm;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class VaccineServiceImpl implements VaccineService {
    private final VaccineRepository vaccineRepository;
    private final DogRepository dogRepository;

    public VaccineServiceImpl(VaccineRepository vaccineRepository, DogRepository dogRepository) {
        this.vaccineRepository = vaccineRepository;
        this.dogRepository = dogRepository;
    }

    /**
     * Creates a new Vaccine object based on the provided form and saves it to the repository.
     *
     * @param form The form containing the information for creating the vaccine.
     * @throws IllegalArgumentException If the form is null.
     * @throws EntityNotFoundException If the dog with the specified ID is not found.
     */
    @Override
    public void create(VaccineForm form) {
        if (form==null) throw new IllegalArgumentException("Le formulaire ne peut être null");
        Vaccine vaccine = Vaccine.builder()
                .disease(form.disease())
                .lastBooster(form.dateBooster())
                .dog(dogRepository.findById(form.dogId()).orElseThrow(()-> new EntityNotFoundException("Chien pas trouvé")))
                .build();
        if (form.disease()== Disease.RAGE) {
            vaccine.setFrequencies(0.33);
        } else vaccine.setFrequencies(1);
        vaccineRepository.save(vaccine);
    }

    @Override
    public Vaccine getOne(long id) {
        return vaccineRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Vaccin pas trouvé"));
    }
}
