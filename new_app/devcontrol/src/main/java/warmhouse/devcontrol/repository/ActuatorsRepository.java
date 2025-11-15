package warmhouse.devcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import warmhouse.devcontrol.dbo.Actuator;

public interface ActuatorsRepository extends JpaRepository<Actuator, Long> {
}
