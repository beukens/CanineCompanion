package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.AppointmentService;
import be.yapock.caninecompanion.pl.models.appointment.AppointmentForm;
import be.yapock.caninecompanion.pl.models.appointment.AppointmentOneDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import be.yapock.caninecompanion.pl.models.appointment.AppointmentAllDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * Retrieves all appointments owned by the specified owner.
     *
     * @param id The ID of the owner
     * @return A ResponseEntity containing a List of AppointmentAllDTO objects representing the appointments owned by the specified owner
     */
    @GetMapping("/owner/{id}")
    public ResponseEntity<List<AppointmentAllDTO>> getAllAppointmentsByOwner(@PathVariable long id) {
        return ResponseEntity.ok(appointmentService.getAllByOwner(id).stream()
                .map(AppointmentAllDTO::fromEntity)
                .toList());
    }

    /**
     * Creates a new appointment using the provided appointment form.
     *
     * @param form the appointment form containing the details of the appointment
     */
    public void create(AppointmentForm form){
        appointmentService.create(form);
    }

    /**
     * Retrieves a specific appointment by its ID.
     *
     * @param id The ID of the appointment to retrieve.
     * @return A ResponseEntity containing the AppointmentOneDTO of the retrieved appointment.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentOneDTO> getOne(@PathVariable long id){
        return ResponseEntity.ok(AppointmentOneDTO.fromEntity(appointmentService.getOne(id)));
    }
}
