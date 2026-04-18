package vetclinic.service;

import org.springframework.stereotype.Service;
import vetclinic.dto.AppointmentCreateRequest;
import vetclinic.model.entity.Appointment;
import vetclinic.model.entity.Pet;
import vetclinic.model.entity.Vet;
import vetclinic.repository.AppointmentRepository;
import vetclinic.repository.PetRepository;
import vetclinic.repository.VetRepository;

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

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Appointment findById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public Appointment save(AppointmentCreateRequest request) {

        if (request.getPetId() == null || request.getVetId() == null) {
            throw new IllegalArgumentException("petId and vetId must not be null");
        }

        Pet pet = petRepository.findById(request.getPetId())
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        Vet vet = vetRepository.findById(request.getVetId())
                .orElseThrow(() -> new RuntimeException("Vet not found"));

        Appointment appointment = new Appointment();
        appointment.setPet(pet);
        appointment.setVet(vet);
        appointment.setAppointmentTime(request.getAppointmentTime());

        return appointmentRepository.save(appointment);
    }

    public Appointment update(Long id, AppointmentCreateRequest request) {

        Appointment existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        if (request.getAppointmentTime() != null) {
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

    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }
}
