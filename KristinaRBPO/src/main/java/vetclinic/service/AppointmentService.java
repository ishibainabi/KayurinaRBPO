package vetclinic.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import vetclinic.dto.AppointmentCreateRequest;
import vetclinic.model.entity.Appointment;
import vetclinic.model.entity.Pet;
import vetclinic.model.entity.Vet;
import vetclinic.repository.AppointmentRepository;
import vetclinic.repository.PetRepository;
import vetclinic.repository.VetRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PetRepository petRepository;
    private final VetRepository vetRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              PetRepository petRepository,
                              VetRepository vetRepository) {
        this.appointmentRepository = appointmentRepository;
        this.petRepository = petRepository;
        this.vetRepository = vetRepository;
    }

    @Transactional
    public Appointment create(AppointmentCreateRequest request) {

        // 1. Валидация входных данных
        if (request.getPetId() == null || request.getVetId() == null) {
            throw new IllegalArgumentException("petId and vetId must not be null");
        }

        if (request.getAppointmentTime() == null) {
            throw new IllegalArgumentException("appointmentTime must not be null");
        }

        // 2. Загружаем сущности
        Pet pet = petRepository.findById(request.getPetId())
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        Vet vet = vetRepository.findById(request.getVetId())
                .orElseThrow(() -> new RuntimeException("Vet not found"));

        // 3. Бизнес-правило: нельзя записаться в прошлое
        if (request.getAppointmentTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Cannot create appointment in the past");
        }

        // 4. Проверка занятости врача
        boolean vetBusy = appointmentRepository
                .existsByVetIdAndAppointmentTime(vet.getId(), request.getAppointmentTime());

        if (vetBusy) {
            throw new RuntimeException("Vet is busy at this time");
        }

        // 5. Проверка занятости питомца
        boolean petBusy = appointmentRepository
                .existsByPetIdAndAppointmentTime(pet.getId(), request.getAppointmentTime());

        if (petBusy) {
            throw new RuntimeException("Pet already has appointment at this time");
        }

        // 6. Создание сущности
        Appointment appointment = new Appointment();
        appointment.setPet(pet);
        appointment.setVet(vet);
        appointment.setAppointmentTime(request.getAppointmentTime());

        // 7. Сохранение
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment update(Long id, AppointmentCreateRequest request) {

        Appointment existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        if (request.getAppointmentTime() != null) {

            if (request.getAppointmentTime().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Cannot move appointment to past");
            }

            existing.setAppointmentTime(request.getAppointmentTime());
        }

        if (request.getPetId() != null) {
            Pet pet = petRepository.findById(request.getPetId())
                    .orElseThrow(() -> new RuntimeException("Pet not found"));
            existing.setPet(pet);
        }

        if (request.getVetId() != null) {
            Vet vet = vetRepository.findById(request.getVetId())
                    .orElseThrow(() -> new RuntimeException("Vet not found"));

            existing.setVet(vet);
        }

        return appointmentRepository.save(existing);
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Appointment findById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }
}
