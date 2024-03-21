package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.pl.models.exercice.ExerciceCheckForm;
import be.yapock.caninecompanion.pl.models.exercice.ExerciceCreateForm;

import be.yapock.caninecompanion.dal.models.Exercice;

public interface ExerciceService {
    void create(ExerciceCreateForm form);
    Exercice getOneById(long id);
    void delete(long id);
    void update(long id, ExerciceCheckForm form);
}
