package be.yapock.caninecompanion.bll.serviceImpls;

import be.yapock.caninecompanion.bll.ActionPlanService;
import be.yapock.caninecompanion.dal.repositories.ActionPlanRepository;
import org.springframework.stereotype.Service;

@Service
public class ActionPlanServiceImpl implements ActionPlanService {
    private final ActionPlanRepository actionPlanRepository;

    public ActionPlanServiceImpl(ActionPlanRepository actionPlanRepository) {
        this.actionPlanRepository = actionPlanRepository;
    }

    /**
     * Deletes an ActionPlan entity by its ID.
     *
     * @param id the ID of the ActionPlan entity to be deleted
     */
    @Override
    public void delete(long id) {
        actionPlanRepository.deleteById(id);
    }
}
