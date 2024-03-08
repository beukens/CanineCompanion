package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.mailing.EMailService;
import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.dal.models.User;
import be.yapock.caninecompanion.dal.models.UserToken;
import be.yapock.caninecompanion.dal.models.enums.UserRole;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import be.yapock.caninecompanion.dal.repositories.UserTokenRepository;
import be.yapock.caninecompanion.dal.repositories.UserRepository;
import be.yapock.caninecompanion.pl.config.security.JWTProvider;
import be.yapock.caninecompanion.pl.config.security.UserTokenConfig;
import be.yapock.caninecompanion.pl.models.user.AuthDTO;
import be.yapock.caninecompanion.pl.models.user.CreateForm;
import be.yapock.caninecompanion.pl.models.user.LoginForm;
import be.yapock.caninecompanion.pl.models.user.PasswordResetRequestForm;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
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
    private UserTokenConfig userTokenConfig;
    @Mock
    private UserTokenRepository userTokenRepository;
    @Mock
    private EMailService eMailService;
    @InjectMocks
    private UserServiceImpl userService;

    private Person person;
    private User user;
    private CreateForm createForm;
    private UserToken userToken;
    private LoginForm loginForm;
    private AuthDTO authDTO;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        person = Person.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("email")
                .build();
        user = User.builder()
                .id(1L)
                .username("johndoe")
                .password("password")
                .userRole(Collections.singletonList(UserRole.ADMIN))
                .person(person)
                .build();
        createForm = new CreateForm("password", "password");
        userToken = UserToken.builder()
                .isAlreadyUsed(false)
                .token("token")
                .emitAt(LocalDateTime.now())
                .lastName("Doe")
                .firstName("john")
                .id(1L)
                .build();
        loginForm = new LoginForm("username", "password");
    }

    @Test
    void sendCreateInvitation_ok() throws MessagingException {
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(userTokenRepository.save(any())).thenReturn(userToken);

        userService.sendCreateInvitation(1L);

        verify(userTokenRepository, times(1)).save(any(UserToken.class));
    }

    @Test
    void sendCreateInvitation_ko_personNotFound(){
        when(personRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> userService.sendCreateInvitation(1L));

        String expectedMessage = "Personne introuvable";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void create_ok() throws IllegalAccessException {
        when(userTokenConfig.validateToken(anyString())).thenReturn(true);
        when(userTokenRepository.findAllByTokenOrderByEmitAtDesc(anyString())).thenReturn(Collections.singletonList(userToken));
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
        when(userTokenConfig.validateToken(anyString())).thenReturn(false);

        Exception exception = assertThrows(IllegalAccessException.class, ()-> userService.create(anyString(),createForm));

        String expectedMessage = "Token invalide";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void create_ko_tokenNotFound(){
        when(userTokenConfig.validateToken(anyString())).thenReturn(true);
        when(userTokenRepository.findAllByTokenOrderByEmitAtDesc(anyString())).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(EntityNotFoundException.class, ()-> userService.create(anyString(),createForm));

        String expectedMessage = "Token introuvable";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void create_ko_tokenExpired(){
        UserToken expiredToken = UserToken.builder()
                .emitAt(LocalDateTime.now().minusMonths(1))
                .build();

        when(userTokenConfig.validateToken(anyString())).thenReturn(true);
        when(userTokenRepository.findAllByTokenOrderByEmitAtDesc(anyString())).thenReturn(Collections.singletonList(expiredToken));

        Exception exception = assertThrows(IllegalArgumentException.class, ()-> userService.create(anyString(),createForm));

        String expectedMessage = "Token expiré";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void create_ko_tokenAlreadyUsed(){
        UserToken usedToken = UserToken.builder()
                .emitAt(LocalDateTime.now())
                .isAlreadyUsed(true)
                .build();

        when(userTokenConfig.validateToken(anyString())).thenReturn(true);
        when(userTokenRepository.findAllByTokenOrderByEmitAtDesc(anyString())).thenReturn(Collections.singletonList(usedToken));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.create(anyString(), createForm));

        String expectedMessage = "Token déjà utilisé";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void create_ko_whenUserAlreadyExists() {
        when(userTokenConfig.validateToken(anyString())).thenReturn(true);
        when(userTokenRepository.findAllByTokenOrderByEmitAtDesc(anyString())).thenReturn(Collections.singletonList(userToken));
        when(personRepository.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(Optional.of(person));
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.create(anyString(), createForm));

        String expectedMessage = "Utilisateur déjà existant";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void login_ok(){
        when(userRepository.findByUsername(loginForm.username())).thenReturn(Optional.of(user));
        when(jwtProvider.generateToken(anyString(),any())).thenReturn("token");

        authDTO = userService.login(loginForm);

        assertEquals(user.getUsername(), authDTO.username());
        assertEquals("token", authDTO.token());
        assertEquals(user.getUserRole(), authDTO.userRoles());
    }

    @Test
    void login_ko_formIsNull(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> userService.login(null));

        String expectedMessage = "le formulaire ne peut être vide";

        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void login_ko_UserNotFound(){
        when(userRepository.findByUsername(loginForm.username())).thenReturn(Optional.empty());

        Exception exception = assertThrows(UsernameNotFoundException.class, () -> userService.login(loginForm));

        String expectedMessage = "Utilisateur introuvable";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void delete_ok(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        userService.delete(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void delete_ko(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> userService.delete(1L));

        String expectedMessage = "Utilisateur introuvable";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void resetPasswordRequest_ok(){
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(userTokenRepository.save(any())).thenReturn(userToken);

        PasswordResetRequestForm passwordResetRequestForm = new PasswordResetRequestForm("login","email");
        userService.resetPasswordRequest(passwordResetRequestForm);

        verify(userTokenRepository,times(1)).save(any(UserToken.class));
    }

    @Test
    void resetPasswordRequest_ko_formIsNull(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> userService.resetPasswordRequest(null));

        String expectedMessage = "Le formulaire ne peut être null";

        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void resetPasswordRequest_ko_usernameNotFound(){
        PasswordResetRequestForm passwordResetRequestForm = new PasswordResetRequestForm("login","email");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(UsernameNotFoundException.class, ()-> userService.resetPasswordRequest(passwordResetRequestForm));

        String expectedMessage = "Utilisateur introuvable";

        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void resetPasswordRequest_ko_EmailDontMatch(){
        PasswordResetRequestForm passwordResetRequestForm = new PasswordResetRequestForm("login","wrongemail");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        Exception exception = assertThrows(IllegalArgumentException.class, ()-> userService.resetPasswordRequest(passwordResetRequestForm));

        String expectedMessage = "Formulaire Invalide";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void updatePassword_ok(){
        when(userTokenRepository.findAllByTokenOrderByEmitAtDesc(anyString())).thenReturn(Collections.singletonList(userToken));
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.updatePassword("token", createForm);

        verify(userRepository, times(1)).save(any());
    }

    @Test
    void updatePassword_ko_formNull(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> userService.updatePassword("token",null));

        String expectedMessage = "Le formulaire ne peut être vide";

        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void updatePassword_ko_tokenNotFound(){
        when(userTokenRepository.findAllByTokenOrderByEmitAtDesc(anyString())).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(EntityNotFoundException.class, ()-> userService.updatePassword(anyString(),createForm));

        String expectedMessage = "Token introuvable";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void updatePassword_ko_tokenExpired(){
        UserToken expiredToken = UserToken.builder()
                .emitAt(LocalDateTime.now().minusMinutes(11))
                .build();
        when(userTokenRepository.findAllByTokenOrderByEmitAtDesc(anyString())).thenReturn(Collections.singletonList(expiredToken));

        Exception exception = assertThrows(IllegalArgumentException.class, ()->userService.updatePassword(anyString(),createForm));

        String expectedMessage = "Token expiré";

        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void update_ko_tokenUsed(){
        UserToken usedToken = UserToken.builder()
                .isAlreadyUsed(true)
                .emitAt(LocalDateTime.now())
                .build();
        when(userTokenRepository.findAllByTokenOrderByEmitAtDesc(anyString())).thenReturn(Collections.singletonList(usedToken));

        Exception exception = assertThrows(IllegalArgumentException.class, ()-> userService.updatePassword(anyString(),createForm));

        String expectedMessage = "Token déjà utilisé";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void update_ko_userNotFound(){
        when(userTokenRepository.findAllByTokenOrderByEmitAtDesc(anyString())).thenReturn(Collections.singletonList(userToken));
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(UsernameNotFoundException.class, ()-> userService.updatePassword(anyString(),createForm));

        String expectedMessage = "Utilisateur non trouvé";

        assertEquals(expectedMessage,exception.getMessage());
    }
}