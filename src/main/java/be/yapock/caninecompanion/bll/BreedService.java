package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.dal.models.Breed;

public interface BreedService {
    Breed getOne(long id);
    void delete(long id);
}
