package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.ActionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ActionPlanRepository extends JpaRepository<ActionPlan, Long> {
    List<ActionPlan> findAllByEndDateAfter(LocalDate date);

    List<ActionPlan> findAllByDog_Id(long id);
}
