package vetclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vetclinic.model.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}