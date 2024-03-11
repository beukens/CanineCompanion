package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.Diagnostic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosticRepository extends JpaRepository<Diagnostic,Long> {
    List<Diagnostic> findAllByDog_Id(long id);
}
