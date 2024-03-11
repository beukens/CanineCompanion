package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.AppointmentService;
import be.yapock.caninecompanion.dal.models.Appointment;
import be.yapock.caninecompanion.dal.repositories.AppointmentRepository;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
     * Retrieves all future appointments.
     * An appointment is considered future if its scheduled date and time is after the current date and time.
     *
     * @return a list of Appointment objects representing the future appointments
     */
    @Override
    public List<Appointment> getAllInFuture() {
        LocalDateTime now = LocalDateTime.now();
        return appointmentRepository.findAllByScheduldedAfter(now);
    }

    /**
     * Retrieves all appointments owned by the specified owner.
     *
     * @param id the ID of the owner
     * @return a list of Appointment objects representing the appointments owned by the specified owner
     */
    @Override
    public List<Appointment> getAllByOwner(long id) {
        return appointmentRepository.findAllByOwner_Id(id);
    }
}
