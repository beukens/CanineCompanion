package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.dal.models.User;
import be.yapock.caninecompanion.dal.models.enums.UserRole;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import be.yapock.caninecompanion.dal.repositories.UserRepository;
import be.yapock.caninecompanion.pl.models.person.PersonForm;
import be.yapock.caninecompanion.pl.models.person.PersonSearchForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {
    @Mock
    private PersonRepository personRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private PersonServiceImpl personService;

    private PersonForm form;
    private Person person;
    private User user;
    private Authentication authentication;

    @BeforeEach
    void setUp(){
        form = new PersonForm("firstName", "lastName", "mail", "phoneNumber", "M",1L);
        person = Person.builder()
                .firstName(form.firstName())
                .lastName(form.lastName())
                .email(form.mail())
                .phoneNumber(form.phoneNumber())
                .gender(form.gender())
                .build();
        user= User.builder()
                .person(person)
                .username("username")
                .password("password")
                .userRole(Collections.singletonList(UserRole.ADMIN))
                .build();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authentication = new UsernamePasswordAuthenticationToken("username", "password", authorities);


    }

    @Test
    void create_shouldWork() {
        when(personRepository.save(any(Person.class))).thenReturn(person);

        personService.create(form);

        verify(personRepository, times(1)).save(any(Person.class));
    }

    @Test
    void create_ko(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> personService.create(null));

        assertEquals("le formulaire ne peut être null", exception.getMessage());
    }

    @Test
    void getOne_ok(){
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        Person result = personService.getOne(1L, authentication);
        assertEquals(person, result);
    }

    @Test
    void getOne_ko_unauthorized(){
        user.setUserRole(Collections.singletonList(UserRole.INTERN));
        user.setPerson(new Person());
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));

        Exception exception = assertThrows(AccessDeniedException.class, ()-> personService.getOne(1L, authentication));

        assertEquals(exception.getMessage(),"Access denied");
    }

    @Test
    void getAll_ok(){
        Pageable pageable = mock(Pageable.class);
        Page entities = mock(Page.class);
        when(personRepository.findAll(pageable)).thenReturn(entities);

        Page result = personService.getAll(pageable);

        assertEquals(entities,result);
    }

    @Test
    void update_ok(){
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        personService.update(1L, form, authentication);

        verify(personRepository, times(1)).save(any(Person.class));
    }

    @Test
    void delete_ok() {
        personService.delete(1L);

        verify(personRepository, times(1)).deleteById(1L);
    }

    @Test
    void search_ok(){
        PersonSearchForm searchForm = new PersonSearchForm("","","");
        List<Person> personList = new ArrayList<>();

        List<Person> result = personService.search(searchForm);

        assertEquals(personList,result);

    }


}