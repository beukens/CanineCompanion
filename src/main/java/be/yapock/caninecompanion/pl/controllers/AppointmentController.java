package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.AppointmentService;
import be.yapock.caninecompanion.pl.models.appointment.AppointmentAllDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /**
     * Retrieves all future appointments.
     *
     * @return A ResponseEntity containing a List of AppointmentAllDTO objects representing the future appointments
     */
    @GetMapping("/future")
    public ResponseEntity<List<AppointmentAllDTO>> getAllFutureAppointments() {
        return ResponseEntity.ok(appointmentService.getAllInFuture().stream()
                .map(AppointmentAllDTO::fromEntity)
                .toList());
    }
}
