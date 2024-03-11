package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.AppointmentService;
import be.yapock.caninecompanion.dal.models.Appointment;
import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.dal.repositories.AppointmentRepository;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import be.yapock.caninecompanion.pl.models.appointment.AppointmentForm;
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
}
