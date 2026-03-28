package vetclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vetclinic.model.entity.Treatment;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
}