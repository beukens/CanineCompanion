package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.pl.models.vaccine.VaccineForm;

import be.yapock.caninecompanion.dal.models.Vaccine;

public interface VaccineService {
    void create(VaccineForm form);
    Vaccine getOne(long id);
}
