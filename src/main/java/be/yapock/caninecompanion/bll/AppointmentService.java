package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.dal.models.Appointment;

public interface AppointmentService {
    Appointment getOne(long id);
}
