package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.PersonService;
import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.dal.models.User;
import be.yapock.caninecompanion.dal.models.enums.UserRole;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import be.yapock.caninecompanion.dal.repositories.UserRepository;
import be.yapock.caninecompanion.pl.models.person.PersonForm;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final UserRepository userRepository;

    public PersonServiceImpl(PersonRepository personRepository, UserRepository userRepository) {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates a new person entity based on the information provided in the
     * given person form and saves it to the person repository.
     *
     * @param form the person form containing the necessary information to create a new person
     */
    @Override
    public void create(PersonForm form) {
        if (form==null)throw new IllegalArgumentException("le formulaire ne peut être null");
        Person person = Person.builder()
                .firstName(form.firstName())
                .lastName(form.lastName())
                .email(form.mail())
                .phoneNumber(form.phoneNumber())
                .gender(form.gender())
                .build();
        personRepository.save(person);
    }

    /**
     * Retrieves a person from the person repository based on the provided ID,
     * if the authentication has the necessary authorities for access.
     *
     * @param id             the ID of the person to retrieve
     * @param authentication the authentication object containing the user's credentials
     * @return the person with the given ID
     * @throws AccessDeniedException    if the authentication does not have the necessary authorities
     * @throws EntityNotFoundException if no person is found with the given ID
     */
    @Override
    public Person getOne(long id, Authentication authentication) {
        if (!hasAuthorities(authentication, id)) {
            throw new AccessDeniedException("Access denied");
        }
        return personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("personne pas trouvée"));
    }



    /**
     * Checks if the authentication has the necessary authorities to access a person with the given ID.
     *
     * @param authentication the authentication object containing the user's credentials
     * @param id             the ID of the person to check access for
     * @return true if the authentication has the necessary authorities, false otherwise
     * @throws UsernameNotFoundException if the user is not found in the user repository
     * @throws EntityNotFoundException  if no person is found with the given ID
     */
    private boolean hasAuthorities(Authentication authentication, long id){
        User userConnected =  userRepository.findByUsername(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("utilisateur non trouvé"));
        return userConnected.getPerson().equals(personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("personne pas trouvée"))) || userConnected.getAuthorities().contains(UserRole.ADMIN);
    }
}
