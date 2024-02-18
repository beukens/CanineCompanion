package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.UserService;
import be.yapock.caninecompanion.bll.mailing.EMailService;
import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.dal.models.UserCreateToken;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import be.yapock.caninecompanion.dal.repositories.UserCreateTokenRepository;
import be.yapock.caninecompanion.dal.repositories.UserRepository;
import be.yapock.caninecompanion.pl.config.security.JWTProvider;
import be.yapock.caninecompanion.pl.config.security.UserCreateTokenConfig;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    public UserServiceImpl(UserRepository userRepository, PersonRepository personRepository, AuthenticationManager authenticationManager, JWTProvider jwtProvider, PasswordEncoder passwordEncoder, UserCreateToken userCreateToken, UserCreateTokenConfig userCreateToken1, UserCreateTokenConfig userCreateTokenConfig, UserCreateTokenRepository userCreateTokenRepository, EMailService eMailService) {
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
     * Sends a create invitation to a person identified by the given id.
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

    @Override
    public void create() {

    }
}
