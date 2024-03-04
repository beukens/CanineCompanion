package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.pl.models.dog.DogCreateForm;
import be.yapock.caninecompanion.pl.models.dog.DogSearchForm;

import java.util.List;

public interface DogService {
    void create(DogCreateForm form);

    List<Dog> search(DogSearchForm form);
}
