package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.UserService;
import be.yapock.caninecompanion.bll.mailing.EMailService;
import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.dal.models.User;
import be.yapock.caninecompanion.dal.models.UserCreateToken;
import be.yapock.caninecompanion.dal.models.enums.UserRole;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import be.yapock.caninecompanion.dal.repositories.UserCreateTokenRepository;
import be.yapock.caninecompanion.dal.repositories.UserRepository;
import be.yapock.caninecompanion.pl.config.security.JWTProvider;
import be.yapock.caninecompanion.pl.config.security.UserCreateTokenConfig;
import be.yapock.caninecompanion.pl.models.user.CreateForm;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    private final UserCreateTokenConfig userCreateTokenConfig;
    private final UserCreateTokenRepository userCreateTokenRepository;
    private final EMailService eMailService;

    public UserServiceImpl(UserRepository userRepository, PersonRepository personRepository, AuthenticationManager authenticationManager, JWTProvider jwtProvider, PasswordEncoder passwordEncoder, UserCreateTokenConfig userCreateToken1, UserCreateTokenConfig userCreateTokenConfig, UserCreateTokenRepository userCreateTokenRepository, EMailService eMailService) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.userCreateTokenConfig = userCreateTokenConfig;
        this.userCreateTokenRepository = userCreateTokenRepository;
        this.eMailService = eMailService;
    }

    /**
     * Sends a creation invitation to a person identified by the given id.
     *
     * @param id The id of the person.
     * @throws MessagingException if there is an error sending the invitation email.
     * @throws EntityNotFoundException if the person with the given id is not found.
     */
    @Override
    public void sendCreateInvitation(long id) throws MessagingException {
        Person person = personRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Person pas trouvée"));
        String token = UserCreateTokenConfig.generateToken(person.getFirstName(), person.getLastName());
        UserCreateToken userCreateToken = UserCreateToken.builder()
                .emitAt(LocalDateTime.now())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .token(token)
                .isAlreadyUsed(false)
                .build();
        userCreateTokenRepository.save(userCreateToken);
        eMailService.sendInvitationEmail(person.getEmail(), person.getFirstName(), person.getLastName(), token);
    }

    /**
     * Creates a new user based on the provided token and form data.
     *
     * @param token The token used for validation.
     * @param form The form data containing the password and confirmed password.
     * @throws IllegalAccessException if the form is invalid or the token is not valid.
     * @throws EntityNotFoundException if the token is not found or the person is not found.
     * @throws IllegalArgumentException if the token is expired or already used.
     */
    @Override
    public void create(String token, CreateForm form) throws IllegalAccessException {
        if (form==null || !form.password().equals(form.confirmedPassword())) throw new IllegalArgumentException("formulaire non valide");
        if (!userCreateTokenConfig.validateToken(token)) throw new IllegalAccessException("token non valide");
        UserCreateToken createToken = userCreateTokenRepository.findAllByTokenOrderByEmitAtDesc(token).stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Token pas trouvé"));

        long hoursDifference = ChronoUnit.HOURS.between(createToken.getEmitAt(), LocalDateTime.now());

        if (hoursDifference > 24) throw new IllegalArgumentException("Token expiré");
        if (createToken.isAlreadyUsed()) throw new IllegalArgumentException("token déja utilisé");

        Person person = personRepository.findByFirstNameAndLastName(createToken.getFirstName(), createToken.getLastName()).orElseThrow(()->new EntityNotFoundException("personne pas trouvée"));

        String username = person.getFirstName().toLowerCase().trim() + person.getLastName().toLowerCase().trim();

        User user = User.builder()
                .username(username)
                .userRole(UserRole.CUSTOMER)
                .password(passwordEncoder.encode(form.password()))
                .person(person)
                .isEnabled(true)
                .build();

        userRepository.save(user);
        createToken.setAlreadyUsed(true);
        userCreateTokenRepository.save(createToken);
        }
    }
