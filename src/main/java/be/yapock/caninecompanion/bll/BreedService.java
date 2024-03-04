package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.dal.models.Breed;

import java.util.List;

public interface BreedService {
    Breed getOne(long id);
    List<Breed> getAll();
}
