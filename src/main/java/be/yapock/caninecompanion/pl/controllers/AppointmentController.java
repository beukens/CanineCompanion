package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.AppointmentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
     * Starts or stops an appointment with the specified ID.
     *
     * @param id the ID of the appointment to start or stop
     */
    @PostMapping("/{id}/startStop")
    public void startStopAppointment(@PathVariable long id) {
        appointmentService.startStop(id);
    }
}
