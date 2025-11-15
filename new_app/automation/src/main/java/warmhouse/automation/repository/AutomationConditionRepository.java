package warmhouse.automation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import warmhouse.automation.dbo.AutomationCondition;
import warmhouse.automation.dbo.User;

import java.util.List;

public interface AutomationConditionRepository extends JpaRepository<AutomationCondition, Long> {
}
