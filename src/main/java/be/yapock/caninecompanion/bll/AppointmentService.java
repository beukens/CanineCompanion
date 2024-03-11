package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.pl.models.appointment.AppointmentForm;

public interface AppointmentService {
    void create(AppointmentForm form);
}
