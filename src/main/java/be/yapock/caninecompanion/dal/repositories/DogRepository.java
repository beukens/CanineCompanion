package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
}
