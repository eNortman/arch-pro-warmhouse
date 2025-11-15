package warmhouse.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warmhouse.webapp.dbo.AutomationCondition;
import warmhouse.webapp.repository.AutomationConditionRepository;

import java.util.List;

@Service
public class AutomationConditionService {

    @Autowired
    private final AutomationConditionRepository automationConditionRepository;

    public AutomationConditionService(AutomationConditionRepository automationConditionRepository) {
        this.automationConditionRepository = automationConditionRepository;
    }

    public List<AutomationCondition> getAll() {
        return automationConditionRepository.findAll();
    }

}
