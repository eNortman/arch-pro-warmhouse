package warmhouse.devcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import warmhouse.devcontrol.dbo.Sensor;

import java.util.List;

public interface SensorsRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findAllByType(String type);
}
