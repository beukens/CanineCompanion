package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.dal.models.Vaccine;

public interface VaccineService {
    Vaccine getOne(long id);
}
