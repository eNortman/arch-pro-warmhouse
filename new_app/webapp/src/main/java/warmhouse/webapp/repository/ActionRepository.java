package warmhouse.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import warmhouse.webapp.dbo.Action;

public interface ActionRepository extends JpaRepository<Action, Long> {
}
