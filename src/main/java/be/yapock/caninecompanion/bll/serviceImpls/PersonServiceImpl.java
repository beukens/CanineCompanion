package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.PersonService;
import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.dal.repositories.PersonRepository;
import be.yapock.caninecompanion.pl.models.PersonForm;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
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
}
