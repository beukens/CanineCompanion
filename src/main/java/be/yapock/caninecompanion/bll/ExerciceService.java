package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.pl.models.exercice.ExerciceCreateForm;

import be.yapock.caninecompanion.dal.models.Exercice;

public interface ExerciceService {
    void create(ExerciceCreateForm form);
    Exercice getOneById(long id);
}
