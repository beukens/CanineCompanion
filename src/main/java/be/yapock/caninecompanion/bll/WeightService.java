package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.pl.models.weight.WeightForm;

import be.yapock.caninecompanion.dal.models.Weight;


import java.util.List;

public interface WeightService {
    void create(WeightForm form);
    Weight getOne(long id);
    List<Weight> getAll(long id);
}
