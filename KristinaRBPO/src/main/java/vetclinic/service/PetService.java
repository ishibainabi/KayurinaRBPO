package vetclinic.service;

import org.springframework.stereotype.Service;
import vetclinic.model.entity.Owner;
import vetclinic.model.entity.Pet;
import vetclinic.repository.OwnerRepository;
import vetclinic.repository.PetRepository;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    public PetService(PetRepository petRepository,
                      OwnerRepository ownerRepository) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
    }

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Pet findById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
    }

    public Pet save(Pet pet) {
        Owner owner = ownerRepository.findById(pet.getOwner().getId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        pet.setOwner(owner);

        return petRepository.save(pet);
    }

    public Pet update(Long id, Pet data) {
        Pet existing = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        existing.setName(data.getName());
        existing.setSpecies(data.getSpecies());

        if (data.getOwner() != null) {
            Owner owner = ownerRepository.findById(data.getOwner().getId())
                    .orElseThrow(() -> new RuntimeException("Owner not found"));

            existing.setOwner(owner);
        }

        return petRepository.save(existing);
    }

    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
}
