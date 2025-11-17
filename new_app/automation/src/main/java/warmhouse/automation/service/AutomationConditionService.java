package warmhouse.automation.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warmhouse.automation.dbo.AutomationCondition;
import warmhouse.automation.repository.AutomationConditionRepository;

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
