package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {
    Optional<Person> findByFirstNameAndLastName(String firstname, String lastName);
}
