package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.dal.models.Diagnostic;

public interface DiagnosticService {
    Diagnostic getOne(long id);
}
