package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.pl.models.appointment.AppointmentForm;

import be.yapock.caninecompanion.dal.models.Appointment;

public interface AppointmentService {
    void create(AppointmentForm form);
    Appointment getOne(long id);
}
