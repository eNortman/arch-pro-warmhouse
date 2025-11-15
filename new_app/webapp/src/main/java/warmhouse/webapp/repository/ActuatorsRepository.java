package warmhouse.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import warmhouse.webapp.dbo.Actuator;

import java.util.List;

public interface ActuatorsRepository extends JpaRepository<Actuator, Long> {
    List<Actuator> getActuatorsByUserId(Long id);
}
