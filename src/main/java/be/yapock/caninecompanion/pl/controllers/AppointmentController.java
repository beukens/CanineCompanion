package be.yapock.caninecompanion.pl.controllers;

import be.yapock.caninecompanion.bll.AppointmentService;
import be.yapock.caninecompanion.pl.models.appointment.AppointmentOneDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
