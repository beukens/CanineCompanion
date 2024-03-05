package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.pl.models.vaccine.VaccineForm;

import be.yapock.caninecompanion.dal.models.Vaccine;

import be.yapock.caninecompanion.dal.models.Vaccine;

import java.util.List;

public interface VaccineService {
    void create(VaccineForm form);
    Vaccine getOne(long id);
    List<Vaccine> gateAllByDog(long id);
    void delete(long id);
}
