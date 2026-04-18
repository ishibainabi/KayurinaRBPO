package vetclinic.service;

import org.springframework.stereotype.Service;
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

    public Appointment save(Appointment a) {

        Pet pet = petRepository.findById(a.getPet().getId())
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        Vet vet = vetRepository.findById(a.getVet().getId())
                .orElseThrow(() -> new RuntimeException("Vet not found"));

        a.setPet(pet);
        a.setVet(vet);

        if (a.getTreatment() != null) {
            a.getTreatment().setAppointment(a);
        }

        return appointmentRepository.save(a);
    }

    public Appointment update(Long id, Appointment data) {
        Appointment existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        existing.setAppointmentTime(data.getAppointmentTime());

        if (data.getPet() != null) {
            Pet pet = petRepository.findById(data.getPet().getId())
                    .orElseThrow(() -> new RuntimeException("Pet not found"));
            existing.setPet(pet);
        }

        if (data.getVet() != null) {
            Vet vet = vetRepository.findById(data.getVet().getId())
                    .orElseThrow(() -> new RuntimeException("Vet not found"));
            existing.setVet(vet);
        }

        return appointmentRepository.save(existing);
    }

    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }
}
