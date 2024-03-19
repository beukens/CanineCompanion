package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.Morphology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MorphologyRepository extends JpaRepository<Morphology, Long> {
}
