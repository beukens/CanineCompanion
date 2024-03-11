package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.DiagnosticService;
import be.yapock.caninecompanion.dal.models.Diagnostic;
import be.yapock.caninecompanion.dal.repositories.DiagnosticRepository;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.pl.models.diagnostic.DiagnosticForm;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;

@Service
public class DiagnosticServiceImpl implements DiagnosticService {
    private final DiagnosticRepository diagnosticRepository;
    private final DogRepository dogRepository;

    public DiagnosticServiceImpl(DiagnosticRepository diagnosticRepository, DogRepository dogRepository) {
        this.diagnosticRepository = diagnosticRepository;
        this.dogRepository = dogRepository;
    }

    /**
     * Creates a new diagnostic using the provided form data.
     *
     * @param form the diagnostic form data
     */
    @Override
    public void create(DiagnosticForm form) {
        if (form==null) throw new IllegalArgumentException("Form ne peut être null");
        Diagnostic diagnostic = Diagnostic.builder()
                .date(LocalDate.now())
                .submissivePosition(form.submissivePosition())
                .withFamiliarHuman(form.withFamiliarHuman())
                .withStranger(form.withStranger())
                .withDogs(form.withDogs())
                .withOtherAnimals(form.withOtherAnimals())
                .stayingAlone(form.stayingAlone())
                .affrayed(form.affrayed())
                .contactWHumans(form.contactWHumans())
                .contactWAnimals(form.contactWAnimals())
                .adaptability(form.adaptability())
                .attachement(form.attachement())
                .separation(form.separation())
                .restPlace(form.restPlace())
                .exploration(form.exploration())
                .tenderness(form.tenderness())
                .vocalize(form.vocalize())
                .jumpOnPeople(form.jumpOnPeople())
                .destruct(form.destruct())
                .scratchesBruises(form.scratchesBruises())
                .excitation(form.excitation())
                .dog(dogRepository.findById(form.dogId()).orElseThrow(()-> new EntityNotFoundException("pas trouvé le woof woof")))
                .build();
        diagnosticRepository.save(diagnostic);
    }
    /**
     * Retrieves a Diagnostic by its id.
     *
     * @param id the id of the Diagnostic to retrieve
     * @return the Diagnostic with the specified id
     * @throws EntityNotFoundException if no Diagnostic is found with the specified id
     */
    @Override
    public Diagnostic getOne(long id) {
        return diagnosticRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Diagnostique pas trouvé"));
    }

    /**
     * Retrieves a list of Diagnostic objects associated with a Dog by its id.
     *
     * @param id the id of the Dog
     * @return a list of Diagnostic objects associated with the Dog
     */
    @Override
    public List<Diagnostic> getAllByDog(long id) {
        return diagnosticRepository.findAllByDog_Id(id);
    }

    /**
     * Deletes a diagnostic record by its ID.
     *
     * @param id the ID of the diagnostic record to be deleted
     */
    @Override
    public void delete(long id) {
        diagnosticRepository.deleteById(id);
    }
}
