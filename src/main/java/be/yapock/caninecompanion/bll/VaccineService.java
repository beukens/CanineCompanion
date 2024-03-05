package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.dal.models.Vaccine;

import java.util.List;

public interface VaccineService {
    List<Vaccine> gateAllByDog(long id);
}
