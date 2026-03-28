package vetclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vetclinic.model.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
}