package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.dal.models.Weight;

import java.util.List;

public interface WeightService {
    List<Weight> getAll(long id);
}
