package warmhouse.automation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warmhouse.automation.dbo.Condition;
import warmhouse.automation.repository.ConditionRepository;

import java.util.Optional;

@Service
public class ConditionService {

    @Autowired
    private ConditionRepository conditionRepository;

    public Optional<Condition> findByConditionId(Long conditionId) {
        return conditionRepository.findById(conditionId);
    }

    public Optional<Condition> findConditionBySensorId(Long sensorId) {
        return conditionRepository.getConditionBySensor_Id(sensorId);
    }

}
