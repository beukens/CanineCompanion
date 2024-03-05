package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    List<Vaccine> findAllByDog_Id(long id);
}
