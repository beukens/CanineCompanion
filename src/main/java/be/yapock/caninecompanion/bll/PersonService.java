package be.yapock.caninecompanion.bll;

import be.yapock.caninecompanion.dal.models.Person;
import be.yapock.caninecompanion.pl.models.person.PersonForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface PersonService {
    void create (PersonForm form);
    Person getOne(long id, Authentication authentication);
    Page<Person> getAll(Pageable pageable);
    void update (long id, PersonForm form, Authentication authentication);
}
