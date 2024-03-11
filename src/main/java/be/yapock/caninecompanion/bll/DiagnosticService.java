package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.pl.models.diagnostic.DiagnosticForm;

import be.yapock.caninecompanion.dal.models.Diagnostic;

import java.util.List;

public interface DiagnosticService {
    void create(DiagnosticForm form);
    Diagnostic getOne(long id);
    List<Diagnostic> getAllByDog(long id);
}
