package vetclinic.service;

import org.springframework.stereotype.Service;
import vetclinic.model.entity.Appointment;
import vetclinic.model.entity.Treatment;
import vetclinic.repository.AppointmentRepository;
import vetclinic.repository.TreatmentRepository;

import java.util.List;

@Service
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final AppointmentRepository appointmentRepository;

    public TreatmentService(TreatmentRepository treatmentRepository,
                            AppointmentRepository appointmentRepository) {
        this.treatmentRepository = treatmentRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Treatment> findAll() {
        return treatmentRepository.findAll();
    }

    public Treatment findById(Long id) {
        return treatmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Treatment not found"));
    }

    public Treatment save(Treatment t) {

        Appointment appointment = appointmentRepository.findById(
                t.getAppointment().getId()
        ).orElseThrow(() -> new RuntimeException("Appointment not found"));

        t.setAppointment(appointment);

        return treatmentRepository.save(t);
    }

    public Treatment update(Long id, Treatment data) {
        Treatment existing = treatmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Treatment not found"));

        existing.setDescription(data.getDescription());

        if (data.getAppointment() != null) {
            Appointment appointment = appointmentRepository.findById(
                    data.getAppointment().getId()
            ).orElseThrow(() -> new RuntimeException("Appointment not found"));

            existing.setAppointment(appointment);
        }

        return treatmentRepository.save(existing);
    }

    public void deleteById(Long id) {
        treatmentRepository.deleteById(id);
    }
}
