package warmhouse.automation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import warmhouse.automation.dbo.Sensor;

public interface SensorsRepository extends JpaRepository<Sensor, Long> {
}
