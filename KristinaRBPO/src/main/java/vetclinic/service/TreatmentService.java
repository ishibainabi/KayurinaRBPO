package vetclinic.service;

import org.springframework.stereotype.Service;
import vetclinic.model.entity.Appointment;
import vetclinic.model.entity.Treatment;
import vetclinic.repository.AppointmentRepository;
import vetclinic.repository.TreatmentRepository;
import vetclinic.dto.TreatmentCreateRequest;

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

    public Treatment save(TreatmentCreateRequest request) {

        if (request.getAppointmentId() == null) {
            throw new RuntimeException("appointmentId is null");
        }

        Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Treatment t = new Treatment();
        t.setDescription(request.getDescription());
        t.setAppointment(appointment);

        return treatmentRepository.save(t);
    }

    public void deleteById(Long id) {
        treatmentRepository.deleteById(id);
    }
}
