package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.PersonService;
import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import be.yapock.caninecompanion.pl.models.PersonForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonServiceImpl personService;

    private PersonForm form;
    private Person person;

    @BeforeEach
    void setUp(){
        form = new PersonForm("firstName", "lastName", "mail", "phoneNumber", 'M');
        person = Person.builder()
                .firstName(form.firstName())
                .lastName(form.lastName())
                .email(form.mail())
                .phoneNumber(form.phoneNumber())
                .gender(form.gender())
                .build();
    }

    @Test
    void create_shouldWork() {
        when(personRepository.save(any(Person.class))).thenReturn(person);

        personService.create(form);

        verify(personRepository, times(1)).save(any(Person.class));
    }
}