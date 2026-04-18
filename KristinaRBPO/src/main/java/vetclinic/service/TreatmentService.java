package vetclinic.service;

import org.springframework.stereotype.Service;
import vetclinic.model.entity.Treatment;
import vetclinic.repository.TreatmentRepository;

import java.util.List;

@Service
public class TreatmentService {
    private final TreatmentRepository treatmentRepository;

    public TreatmentService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public List<Treatment> findAll() {
        return treatmentRepository.findAll();
    }

    public Treatment findById(Long id) {
        return treatmentRepository.findById(id).orElse(null);
    }

    public Treatment save(Treatment treatment) {
        return treatmentRepository.save(treatment);
    }

    public Treatment update(Long id, Treatment treatment) {
        treatment.setId(id);
        return treatmentRepository.save(treatment);
    }

    public void deleteById(Long id) {
        treatmentRepository.deleteById(id);
    }
}
