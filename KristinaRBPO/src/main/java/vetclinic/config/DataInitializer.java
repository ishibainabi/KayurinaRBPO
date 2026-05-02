package vetclinic.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(OwnerRepository ownerRepository,
                           PetRepository petRepository,
                           VetRepository vetRepository,
                           AppointmentRepository appointmentRepository,
                           TreatmentRepository treatmentRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {

        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.vetRepository = vetRepository;
        this.appointmentRepository = appointmentRepository;
        this.treatmentRepository = treatmentRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        // --- USERS ---
        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@mail.com");
        admin.setPassword(passwordEncoder.encode("Admin123!"));
        admin.setRole(Role.ADMIN);

        User user = new User();
        user.setUsername("user");
        user.setEmail("user@mail.com");
        user.setPassword(passwordEncoder.encode("User123!"));
        user.setRole(Role.USER);

        userRepository.save(admin);
        userRepository.save(user);

        // --- OWNERS ---
        Owner owner1 = new Owner("Alice", "alice@mail.com");
        Owner owner2 = new Owner("Bob", "bob@mail.com");
        ownerRepository.save(owner1);
        ownerRepository.save(owner2);

        // --- PETS ---
        Pet pet1 = new Pet("Fluffy", "Cat", owner1);
        Pet pet2 = new Pet("Rex", "Dog", owner2);
        petRepository.save(pet1);
        petRepository.save(pet2);

        // --- VETS ---
        Vet vet1 = new Vet("Dr. Smith", "Surgery");
        Vet vet2 = new Vet("Dr. Jones", "Dentistry");
        vetRepository.save(vet1);
        vetRepository.save(vet2);

        // --- APPOINTMENTS ---
        Appointment app1 = new Appointment(pet1, vet1, LocalDateTime.now().plusDays(1));
        Appointment app2 = new Appointment(pet2, vet2, LocalDateTime.now().plusDays(2));
        appointmentRepository.save(app1);
        appointmentRepository.save(app2);

        // --- TREATMENTS ---
        Treatment treatment1 = new Treatment("Vaccination", app1);
        Treatment treatment2 = new Treatment("Dental cleaning", app2);
        treatmentRepository.save(treatment1);
        treatmentRepository.save(treatment2);
    }
}
