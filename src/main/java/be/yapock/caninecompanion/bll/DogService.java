package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.pl.models.dog.DogCreateForm;
import be.yapock.caninecompanion.pl.models.dog.DogSearchForm;

import java.util.List;

public interface DogService {
    void create(DogCreateForm form);
    Dog getDogById(Long id);

    List<Dog> search(DogSearchForm form);
    List<Dog> findAllByOwner(long id);
    void update(DogCreateForm form, long id);
    void delete(long id);
}
