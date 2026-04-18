package vetclinic.service;

import org.springframework.stereotype.Service;
import vetclinic.model.entity.Pet;
import vetclinic.repository.PetRepository;

import java.util.List;

@Service
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Pet findById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet update(Long id, Pet data) {
        Pet existing = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        existing.setName(data.getName());
        existing.setSpecies(data.getSpecies());
        existing.setOwner(data.getOwner());

        return petRepository.save(existing);
    }

    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
}
