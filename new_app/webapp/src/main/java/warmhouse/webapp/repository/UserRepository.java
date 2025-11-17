package warmhouse.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import warmhouse.webapp.dbo.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
