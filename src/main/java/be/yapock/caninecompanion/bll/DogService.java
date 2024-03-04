package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.pl.models.dog.DogCreateForm;

public interface DogService {
    void create(DogCreateForm form);
    Dog getDogById(Long id);
}
