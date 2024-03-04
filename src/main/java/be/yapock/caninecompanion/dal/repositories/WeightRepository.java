package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Long> {
    Optional<Weight> findByDog_Id(long id);
}
