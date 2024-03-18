package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.ActionPlanService;
import be.yapock.caninecompanion.dal.models.ActionPlan;
import be.yapock.caninecompanion.dal.repositories.ActionPlanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ActionPlanServiceImpl implements ActionPlanService {
    private final ActionPlanRepository actionPlanRepository;

    public ActionPlanServiceImpl(ActionPlanRepository actionPlanRepository) {
        this.actionPlanRepository = actionPlanRepository;
    }

    /**
     * Retrieves a specific ActionPlan object by its ID.
     *
     * @param id The ID of the ActionPlan to retrieve.
     * @return The ActionPlan object with the specified ID.
     * @throws EntityNotFoundException if no ActionPlan with the specified ID exists.
     */
    @Override
    public ActionPlan getOneById(Long id) {
        return actionPlanRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Plan d'action pas trouvé"));
    }
}
