package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.dal.models.Breed;
import be.yapock.caninecompanion.pl.models.breed.BreedForm;

public interface BreedService {
    Breed getOne(long id);
    void create(BreedForm form);
}
