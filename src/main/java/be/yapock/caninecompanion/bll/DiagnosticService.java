package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.pl.models.diagnostic.DiagnosticForm;

public interface DiagnosticService {
    void create(DiagnosticForm form);
}
