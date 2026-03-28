package vetclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vetclinic.model.entity.UserSession;

public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
}