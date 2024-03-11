package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.dal.models.Diagnostic;

import java.util.List;

public interface DiagnosticService {
    Diagnostic getOne(long id);
    List<Diagnostic> getAllByDog(long id);
}
