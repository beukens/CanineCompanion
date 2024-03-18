package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciceRepository extends JpaRepository<Exercice, Long> {
}
