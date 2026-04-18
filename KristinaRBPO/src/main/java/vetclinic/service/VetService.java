package vetclinic.service;

import org.springframework.stereotype.Service;
import vetclinic.model.entity.Vet;
import vetclinic.repository.VetRepository;

import java.util.List;

@Service
public class VetService {

    private final VetRepository vetRepository;

    public VetService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    public List<Vet> findAll() {
        return vetRepository.findAll();
    }

    public Vet findById(Long id) {
        return vetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vet not found"));
    }

    public Vet save(Vet vet) {
        return vetRepository.save(vet);
    }

    public Vet update(Long id, Vet data) {
        Vet existing = vetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vet not found"));

        existing.setName(data.getName());
        existing.setSpecialization(data.getSpecialization());

        return vetRepository.save(existing);
    }

    public void deleteById(Long id) {
        vetRepository.deleteById(id);
    }
}
