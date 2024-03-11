package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.AppointmentService;
import be.yapock.caninecompanion.dal.models.Appointment;
import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.dal.models.Appointment;
import be.yapock.caninecompanion.dal.models.Appointment;
import be.yapock.caninecompanion.dal.models.Appointment;
import be.yapock.caninecompanion.dal.repositories.AppointmentRepository;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import be.yapock.caninecompanion.pl.models.appointment.AppointmentForm;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
     * Creates a new appointment based on the provided form data.
     *
     * @param form The appointment form containing the necessary data.
     */
    public void create(AppointmentForm form){
        Appointment appointment = Appointment.builder()
                .schedulded(form.scheduled())
                .comment(form.comment())
                .dogs(dogRepository.findAllById(form.dogIds()))
                .build();
        Person owner = appointment.getDogs().getFirst().getOwner();
        appointment.setOwner(owner);
        appointment.setFirstMeeting(appointmentRepository.findAllByOwner_Id(owner.getId()).isEmpty());
        appointmentRepository.save(appointment);
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

    /**
     * Deletes an appointment by its ID.
     *
     * @param id The ID of the appointment to be deleted.
     */
    @Override
    public void delete(long id) {
        appointmentRepository.deleteById(id);
    }
}
