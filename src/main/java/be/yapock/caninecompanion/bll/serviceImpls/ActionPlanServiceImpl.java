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
}
