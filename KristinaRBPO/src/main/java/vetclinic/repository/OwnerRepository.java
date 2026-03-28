package vetclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vetclinic.model.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}