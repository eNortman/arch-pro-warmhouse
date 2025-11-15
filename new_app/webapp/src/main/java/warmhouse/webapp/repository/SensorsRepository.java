package warmhouse.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import warmhouse.webapp.dbo.Sensor;

import java.util.List;

public interface SensorsRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> getSensorsByUserId(Long userId);
}
