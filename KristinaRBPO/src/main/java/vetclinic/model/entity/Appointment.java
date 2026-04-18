package vetclinic.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_seq")
    @SequenceGenerator(name = "appointment_seq", sequenceName = "appointment_seq", allocationSize = 50)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "vet_id", nullable = false)
    @JsonIgnore
    private Vet vet;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Treatment treatment;

    public Appointment() {}
    public Appointment(Pet pet, Vet vet, LocalDateTime appointmentTime) {
        this.pet = pet;
        this.vet = vet;
        this.appointmentTime = appointmentTime;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Pet getPet() { return pet; }
    public void setPet(Pet pet) { this.pet = pet; }
    public Vet getVet() { return vet; }
    public void setVet(Vet vet) { this.vet = vet; }
    public LocalDateTime getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(LocalDateTime appointmentTime) { this.appointmentTime = appointmentTime; }
    public Treatment getTreatment() { return treatment; }
    public void setTreatment(Treatment treatment) { this.treatment = treatment; }
}
