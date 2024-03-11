package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.DiagnosticService;
import be.yapock.caninecompanion.dal.repositories.DiagnosticRepository;
import org.springframework.stereotype.Service;

@Service
public class DiagnosticServiceImpl implements DiagnosticService {
    private final DiagnosticRepository diagnosticRepository;

    public DiagnosticServiceImpl(DiagnosticRepository diagnosticRepository) {
        this.diagnosticRepository = diagnosticRepository;
    }
}
