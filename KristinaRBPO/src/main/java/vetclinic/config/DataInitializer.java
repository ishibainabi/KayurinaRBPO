package vetclinic.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vetclinic.model.entity.*;
import vetclinic.repository.*;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final VetRepository vetRepository;
    private final AppointmentRepository appointmentRepository;
    private final TreatmentRepository treatmentRepository;

    public DataInitializer(OwnerRepository ownerRepository, PetRepository petRepository,
                           VetRepository vetRepository, AppointmentRepository appointmentRepository,
                           TreatmentRepository treatmentRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.vetRepository = vetRepository;
        this.appointmentRepository = appointmentRepository;
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner("Alice", "alice@mail.com");
        Owner owner2 = new Owner("Bob", "bob@mail.com");
        ownerRepository.save(owner1);
        ownerRepository.save(owner2);

        Pet pet1 = new Pet("Fluffy", "Cat", owner1);
        Pet pet2 = new Pet("Rex", "Dog", owner2);
        petRepository.save(pet1);
        petRepository.save(pet2);

        Vet vet1 = new Vet("Dr. Smith", "Surgery");
        Vet vet2 = new Vet("Dr. Jones", "Dentistry");
        vetRepository.save(vet1);
        vetRepository.save(vet2);

        Appointment app1 = new Appointment(pet1, vet1, LocalDateTime.now().plusDays(1));
        Appointment app2 = new Appointment(pet2, vet2, LocalDateTime.now().plusDays(2));
        appointmentRepository.save(app1);
        appointmentRepository.save(app2);

        Treatment treatment1 = new Treatment("Vaccination", app1);
        Treatment treatment2 = new Treatment("Dental cleaning", app2);
        treatmentRepository.save(treatment1);
        treatmentRepository.save(treatment2);
    }
}