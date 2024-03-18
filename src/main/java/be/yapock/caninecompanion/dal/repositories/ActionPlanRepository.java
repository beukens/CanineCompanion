package be.yapock.caninecompanion.dal.repositories;

import be.yapock.caninecompanion.dal.models.ActionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionPlanRepository extends JpaRepository<ActionPlan, Long> {
}
