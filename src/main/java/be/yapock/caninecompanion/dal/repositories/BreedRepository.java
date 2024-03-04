package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreedRepository extends JpaRepository<Breed,Long> {
}
