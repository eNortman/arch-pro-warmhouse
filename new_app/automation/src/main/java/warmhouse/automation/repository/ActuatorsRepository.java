package warmhouse.automation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import warmhouse.automation.dbo.Actuator;

public interface ActuatorsRepository extends JpaRepository<Actuator, Long> {
}
