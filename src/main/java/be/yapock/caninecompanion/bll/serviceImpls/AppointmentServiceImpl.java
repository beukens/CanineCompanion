package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.AppointmentService;
import be.yapock.caninecompanion.dal.models.Appointment;
import be.yapock.caninecompanion.dal.repositories.AppointmentRepository;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DogRepository dogRepository;
    private final PersonRepository personRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, DogRepository dogRepository, PersonRepository personRepository) {
        this.appointmentRepository = appointmentRepository;
        this.dogRepository = dogRepository;
        this.personRepository = personRepository;
    }

    /**
     * This method starts or stops an appointment.
     *
     * @param id The ID of the appointment.
     * @throws EntityNotFoundException If the appointment is not found.
     */
    @Override
    public void startStop(long id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(()->new EntityNotFoundException("RDV pas trouvé"));
        if (appointment.getStart().equals(null)) appointment.setStart(LocalTime.now());
        else appointment.setEnd(LocalTime.now());
        appointmentRepository.save(appointment);
    }
}
