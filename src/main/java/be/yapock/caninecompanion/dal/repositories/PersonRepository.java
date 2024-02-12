package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
