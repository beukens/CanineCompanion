package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
}
