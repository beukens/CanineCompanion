package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.pl.models.vaccine.VaccineUpdateForm;

public interface VaccineService {
    void update(VaccineUpdateForm form, long id);
}
