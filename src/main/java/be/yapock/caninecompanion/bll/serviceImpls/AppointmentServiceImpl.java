package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.AppointmentService;
import be.yapock.caninecompanion.dal.models.Appointment;
import be.yapock.caninecompanion.dal.repositories.AppointmentRepository;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

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
     * Retrieves an appointment by its ID.
     *
     * @param id The ID of the appointment to retrieve.
     * @return The appointment with the specified ID.
     * @throws EntityNotFoundException if the appointment is not found.
     */
    @Override
    public Appointment getOne(long id) {
        return appointmentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("RDV pas trouvé"));
    }
}
