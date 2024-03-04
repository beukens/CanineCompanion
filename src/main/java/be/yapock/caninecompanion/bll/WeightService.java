package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.pl.models.weight.WeightForm;

import be.yapock.caninecompanion.dal.models.Weight;

public interface WeightService {
    void create(WeightForm form);
    Weight getOne(long id);
}
