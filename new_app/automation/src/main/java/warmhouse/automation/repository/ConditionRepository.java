package warmhouse.automation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import warmhouse.automation.dbo.Condition;

import java.util.Optional;

public interface ConditionRepository extends JpaRepository<Condition, Long> {
    Optional<Condition> getConditionBySensor_Id(Long sensorId);
}
