package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.dal.models.Morphology;
import be.yapock.caninecompanion.pl.models.morphology.MorphologyForm;

public interface MorphologyService {
    Morphology getOne(long id);
    long create(MorphologyForm form);
    void delete(long id);
}
