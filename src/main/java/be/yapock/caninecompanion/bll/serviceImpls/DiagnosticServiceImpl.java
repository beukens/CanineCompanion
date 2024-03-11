package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.DiagnosticService;
import be.yapock.caninecompanion.dal.models.Diagnostic;
import be.yapock.caninecompanion.dal.repositories.DiagnosticRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosticServiceImpl implements DiagnosticService {
    private final DiagnosticRepository diagnosticRepository;

    public DiagnosticServiceImpl(DiagnosticRepository diagnosticRepository) {
        this.diagnosticRepository = diagnosticRepository;
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
}
