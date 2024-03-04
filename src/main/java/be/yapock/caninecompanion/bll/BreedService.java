package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.dal.models.Breed;
import be.yapock.caninecompanion.pl.models.breed.BreedForm;

import java.util.List;

public interface BreedService {
    Breed getOne(long id);
    void create(BreedForm form);
    void delete(long id);
    List<Breed> getAll();
}
