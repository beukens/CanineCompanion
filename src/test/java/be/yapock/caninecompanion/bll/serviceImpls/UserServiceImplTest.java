package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.UserService;
import be.yapock.caninecompanion.bll.mailing.EMailService;
import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.dal.models.User;
import be.yapock.caninecompanion.dal.models.UserCreateToken;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import be.yapock.caninecompanion.dal.repositories.UserCreateTokenRepository;
import be.yapock.caninecompanion.dal.repositories.UserRepository;
import be.yapock.caninecompanion.pl.config.security.JWTProvider;
import be.yapock.caninecompanion.pl.config.security.UserCreateTokenConfig;
import be.yapock.caninecompanion.pl.models.user.CreateForm;
import be.yapock.caninecompanion.pl.models.validation.validators.EmailValidator;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JWTProvider jwtProvider;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserCreateTokenConfig userCreateTokenConfig;
    @Mock
    private UserCreateTokenRepository userCreateTokenRepository;
    @Mock
    private EMailService eMailService;
    @InjectMocks
    private UserServiceImpl userService;

    private Person person;
    private User user;
    private CreateForm createForm;
    private UserCreateToken userCreateToken;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        person = Person.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .build();
        user = User.builder()
                .id(1L)
                .username("johndoe")
                .password("password")
                .build();
        createForm = new CreateForm("password", "password");
        userCreateToken = UserCreateToken.builder()
                .isAlreadyUsed(false)
                .token("token")
                .emitAt(LocalDateTime.now())
                .lastName("Doe")
                .firstName("john")
                .id(1L)
                .build();
    }



    @Test
    void create_ok() throws IllegalAccessException {
        when(userCreateTokenConfig.validateToken(anyString())).thenReturn(true);
        when(userCreateTokenRepository.findAllByTokenOrderByEmitAtDesc(anyString())).thenReturn(Collections.singletonList(userCreateToken));
        when(personRepository.findByFirstNameAndLastName(anyString(),anyString())).thenReturn(Optional.of(person));
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.create(anyString(), createForm);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void create_ko_whenFormNull(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> userService.create(anyString(),null));

        String expectedMessage = "formulaire non valide";

        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void create_ko_whenPasswordNotEquals(){
        CreateForm wrongForm = new CreateForm("password", "notapassword");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.create(anyString(), wrongForm));

        String expectedMessage = "formulaire non valide";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void create_ko_wrongToken(){
        when(userCreateTokenConfig.validateToken(anyString())).thenReturn(false);

        Exception exception = assertThrows(IllegalAccessException.class, ()-> userService.create(anyString(),createForm));

        String expectedMessage = "Token invalide";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void create_ko_tokenNotFound(){
        when(userCreateTokenConfig.validateToken(anyString())).thenReturn(true);
        when(userCreateTokenRepository.findAllByTokenOrderByEmitAtDesc(anyString())).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(EntityNotFoundException.class, ()-> userService.create(anyString(),createForm));

        String expectedMessage = "Token introuvable";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void create_ko_tokenExpired(){
        UserCreateToken expiredToken = UserCreateToken.builder()
                .emitAt(LocalDateTime.now().minusMonths(1))
                .build();

        when(userCreateTokenConfig.validateToken(anyString())).thenReturn(true);
        when(userCreateTokenRepository.findAllByTokenOrderByEmitAtDesc(anyString())).thenReturn(Collections.singletonList(expiredToken));

        Exception exception = assertThrows(IllegalArgumentException.class, ()-> userService.create(anyString(),createForm));

        String expectedMessage = "Token expiré";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void create_ko_tokenAlreadyUsed(){
        UserCreateToken usedToken = UserCreateToken.builder()
                .emitAt(LocalDateTime.now())
                .isAlreadyUsed(true)
                .build();

        when(userCreateTokenConfig.validateToken(anyString())).thenReturn(true);
        when(userCreateTokenRepository.findAllByTokenOrderByEmitAtDesc(anyString())).thenReturn(Collections.singletonList(usedToken));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.create(anyString(), createForm));

        String expectedMessage = "Token déjà utilisé";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void create_ko_whenUserAlreadyExists() {
        when(userCreateTokenConfig.validateToken(anyString())).thenReturn(true);
        when(userCreateTokenRepository.findAllByTokenOrderByEmitAtDesc(anyString())).thenReturn(Collections.singletonList(userCreateToken));
        when(personRepository.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(Optional.of(person));
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.create(anyString(), createForm));

        String expectedMessage = "Utilisateur déjà existant";

        assertEquals(expectedMessage, exception.getMessage());
    }
}