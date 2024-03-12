package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.dal.models.Appointment;
import be.yapock.caninecompanion.dal.models.Dog;
import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.dal.repositories.AppointmentRepository;
import be.yapock.caninecompanion.dal.repositories.DogRepository;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import be.yapock.caninecompanion.pl.models.appointment.AppointmentForm;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceImplTest {
    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private DogRepository dogRepository;
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private AppointmentServiceImpl appointmentService;
    private Appointment appointment;
    private AppointmentForm form;
    private Dog dog;
    private Person owner;

    @BeforeEach
    void setUp() {
        appointment = Appointment.builder()
                .id(4L)
                .build();
        form = new AppointmentForm(LocalDateTime.now(), "comment", List.of(1L, 2L));
        owner = Person.builder()
                .id(4L)
                .firstName("firstname")
                .lastName("lastname")
                .email("mail")
                .phoneNumber("0470013000")
                .gender("gender")
                .build();
        dog = Dog.builder()
                .id(1L)
                .owner(owner)
                .build();
    }

    @Test
    void create_ok() {
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);
        when(dogRepository.findAllById(anyList())).thenReturn(List.of(dog));
        appointmentService.create(form);
        verify(appointmentRepository, times(1)).save(any());
    }

    @Test
    void create_ko_formIsNull(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> appointmentService.create(null));
        assertEquals("Form ne peut être null", exception.getMessage());
    }

    @Test
    void getOne_ok() {
        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(appointment));
        Appointment result = appointmentService.getOne(4L);
        assertEquals(appointment, result);
    }

    @Test
    void getOne_ko(){
        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> appointmentService.getOne(4L));
        assertEquals("RDV pas trouvé", exception.getMessage());
    }

    @Test
    void getAllInFuture() {
        when(appointmentRepository.findAllByScheduldedAfter(any())).thenReturn(Collections.singletonList(appointment));
        List<Appointment> result = appointmentService.getAllInFuture();
        assertEquals(Collections.singletonList(appointment), result);
    }

    @Test
    void getAllByOwner() {
        when(appointmentRepository.findAllByOwner_Id(anyLong())).thenReturn(Collections.singletonList(appointment));
        List<Appointment> result = appointmentService.getAllByOwner(4L);
        assertEquals(Collections.singletonList(appointment), result);
    }

    @Test
    void startStop_ok() {
        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(appointment));
        appointmentService.startStop(4L);
        verify(appointmentRepository, times(1)).save(any());
    }

    @Test
    void startStop_ko_NotFound(){
        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> appointmentService.startStop(4L));
        assertEquals("RDV pas trouvé", exception.getMessage());
    }

    @Test
    void delete() {
        appointmentService.delete(anyLong());
        verify(appointmentRepository, times(1)).deleteById(anyLong());
    }
}