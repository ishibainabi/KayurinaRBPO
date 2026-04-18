package vetclinic.service;

import org.springframework.stereotype.Service;
import vetclinic.model.entity.Appointment;
import vetclinic.model.entity.Pet;
import vetclinic.model.entity.Vet;
import vetclinic.repository.AppointmentRepository;
import vetclinic.repository.PetRepository;
import vetclinic.repository.VetRepository;
import vetclinic.dto.AppointmentCreateRequest;

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

        Pet pet = petRepository.findById(request.getPetId())
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        Vet vet = vetRepository.findById(request.getVetId())
                .orElseThrow(() -> new RuntimeException("Vet not found"));

        Appointment a = new Appointment();
        a.setPet(pet);
        a.setVet(vet);
        a.setAppointmentTime(request.getAppointmentTime());

        return appointmentRepository.save(a);
    }

    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }
}
