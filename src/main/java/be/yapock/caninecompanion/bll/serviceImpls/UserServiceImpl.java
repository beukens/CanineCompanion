package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.UserService;
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
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    private final UserTokenConfig userTokenConfig;
    private final UserTokenRepository userTokenRepository;
    private final EMailService eMailService;

    public UserServiceImpl(UserRepository userRepository, PersonRepository personRepository, AuthenticationManager authenticationManager, JWTProvider jwtProvider, PasswordEncoder passwordEncoder, UserTokenConfig userCreateToken1, UserTokenConfig userTokenConfig, UserTokenRepository userTokenRepository, EMailService eMailService) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.userTokenConfig = userTokenConfig;
        this.userTokenRepository = userTokenRepository;
        this.eMailService = eMailService;
    }

    /**
     * Sends a creation invitation to a person identified by the given id.
     *
     * @param id The id of the person.
     * @throws MessagingException      if there is an error sending the invitation email.
     * @throws EntityNotFoundException if the person with the given id is not found.
     */
    @Override
    public void sendCreateInvitation(long id) throws MessagingException {
        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Personne introuvable"));
        String token = UserTokenConfig.generateToken(person.getFirstName(), person.getLastName());
        UserToken userToken = UserToken.builder()
                .emitAt(LocalDateTime.now())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .token(token)
                .isAlreadyUsed(false)
                .build();
        userTokenRepository.save(userToken);
        eMailService.sendInvitationEmail(person.getEmail(), person.getFirstName(), person.getLastName(), token);
    }

    /**
     * Creates a new user based on the provided token and form data.
     *
     * @param token The token used for validation.
     * @param form  The form data containing the password and confirmed password.
     * @throws IllegalAccessException   if the form is invalid or the token is not valid.
     * @throws EntityNotFoundException  if the token is not found or the person is not found.
     * @throws IllegalArgumentException if the token is expired or already used.
     */
    @Override
    public void create(String token, CreateForm form) throws IllegalAccessException {
        if (form == null || !form.password().equals(form.confirmedPassword()))
            throw new IllegalArgumentException("formulaire non valide");
        if (!userTokenConfig.validateToken(token)) throw new IllegalAccessException("Token invalide");
        UserToken createToken = userTokenRepository.findAllByTokenOrderByEmitAtDesc(token).stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Token introuvable"));

        long hoursDifference = ChronoUnit.HOURS.between(createToken.getEmitAt(), LocalDateTime.now());

        if (hoursDifference > 24) throw new IllegalArgumentException("Token expiré");
        if (createToken.isAlreadyUsed()) throw new IllegalArgumentException("Token déjà utilisé");


        Person person = personRepository.findByFirstNameAndLastName(createToken.getFirstName(), createToken.getLastName()).orElseThrow(() -> new EntityNotFoundException("personne pas trouvée"));

        String username = person.getFirstName().toLowerCase().trim() + person.getLastName().toLowerCase().trim();
        if (userRepository.existsByUsername(username)) throw new IllegalArgumentException("Utilisateur déjà existant");
        User user = User.builder()
                .username(username)
                .userRole(Collections.singletonList(UserRole.CUSTOMER))
                .password(passwordEncoder.encode(form.password()))
                .person(person)
                .isEnabled(true)
                .build();

        userRepository.save(user);
        createToken.setAlreadyUsed(true);
        userTokenRepository.save(createToken);
    }

    /**
     * Authenticates a user by performing the login process.
     *
     * @param form The login form containing the username and password.
     * @return An AuthDTO object containing the authentication token, username, and user roles.
     * @throws IllegalArgumentException   If the login form is empty.
     * @throws UsernameNotFoundException  If the user with the given username is not found.
     * @throws AuthenticationException    If the authentication fails.
     */
    @Override
    public AuthDTO login(LoginForm form) {
        if (form == null) throw new IllegalArgumentException("le formulaire ne peut être vide");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.username(), form.password()));
        User user = userRepository.findByUsername(form.username()).orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
        String token = jwtProvider.generateToken(user.getUsername(), user.getUserRole());
        return new AuthDTO(token, user.getUsername(), user.getUserRole());
    }

    /**
     * Deletes a user with the given ID by disabling their account.
     *
     * @param id The ID of the user to be deleted.
     * @throws EntityNotFoundException if the user with the given ID is not found.
     */
    @Override
    public void delete(long id){
        User user = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Utilisateur introuvable"));
        user.setEnabled(false);
        userRepository.save(user);
    }

    /**
     * Resets the password for a user by generating a password reset token, saving it to the database,
     * and sending an email notification to the user with a password reset link.
     *
     * @param form The form data containing the user's login and email.
     * @throws UsernameNotFoundException   If the user with the given username is not found.
     * @throws IllegalArgumentException  If the email in the form does not match the user's email.
     */
    @SneakyThrows
    @Override
    public void resetPasswordRequest(PasswordResetRequestForm form) {
        User user = userRepository.findByUsername(form.login()).orElseThrow(()-> new UsernameNotFoundException("Utilisateur introuvable"));
        if (!user.getPerson().getEmail().equals(form.email())) throw new IllegalArgumentException("Formulaire Invalide");
        String token = UserTokenConfig.generateToken(user.getPerson().getFirstName(), user.getPerson().getLastName());
        userTokenRepository.save(UserToken.builder()
                .emitAt(LocalDateTime.now())
                .firstName(user.getPerson().getFirstName())
                .lastName(user.getPerson().getLastName())
                .token(token)
                .isAlreadyUsed(false)
                .build());
        eMailService.sendPasswordResetEmail(user.getPerson().getFirstName(), user.getPerson().getEmail(), token);
    }
}
