package vetclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vetclinic.model.entity.Appointment;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Проверка занятости врача
    boolean existsByVetIdAndAppointmentTime(Long vetId, LocalDateTime appointmentTime);

    // (опционально) Проверка занятости питомца
    boolean existsByPetIdAndAppointmentTime(Long petId, LocalDateTime appointmentTime);
}
