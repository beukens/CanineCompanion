package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Long> {
    List<Weight> findAllByDog_Id(long id);
}
