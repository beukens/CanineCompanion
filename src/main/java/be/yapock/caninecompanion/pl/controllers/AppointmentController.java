package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.AppointmentService;
import be.yapock.caninecompanion.pl.models.appointment.AppointmentForm;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /**
     * Creates a new appointment using the provided appointment form.
     *
     * @param form the appointment form containing the details of the appointment
     */
    public void create(AppointmentForm form){
        appointmentService.create(form);
    }
}
