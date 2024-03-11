package be.yapock.caninecompanion.bll;


import be.yapock.caninecompanion.dal.models.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAllInFuture();
}
