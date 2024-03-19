package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.bll.ExerciceService;
import be.yapock.caninecompanion.dal.models.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciceRepository extends JpaRepository<Exercice, Long> {
    boolean existsByNameEqualsIgnoreCaseAndDescriptionEqualsIgnoreCase(String name, String description);
    Exercice findByNameEqualsIgnoreCaseAndDescriptionEqualsIgnoreCase(String name, String description);
}
