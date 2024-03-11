package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.pl.models.appointment.AppointmentForm;

import be.yapock.caninecompanion.dal.models.Appointment;


import be.yapock.caninecompanion.dal.models.Appointment;

import java.util.List;

public interface AppointmentService {
    void create(AppointmentForm form);
    Appointment getOne(long id);
    List<Appointment> getAllInFuture();
    List<Appointment> getAllByOwner(long id);
    void startStop(long id);
    void delete(long id);
}
