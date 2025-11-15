package warmhouse.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import warmhouse.webapp.dbo.AutomationCondition;

import java.util.List;

public interface AutomationConditionRepository extends JpaRepository<AutomationCondition, Long> {
    List<AutomationCondition> getActuatorsByUserId(Long id);
}
