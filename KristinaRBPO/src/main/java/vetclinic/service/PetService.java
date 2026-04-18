package vetclinic.service;

import org.springframework.stereotype.Service;
import vetclinic.model.entity.Owner;
import vetclinic.model.entity.Pet;
import vetclinic.repository.OwnerRepository;
import vetclinic.repository.PetRepository;
import vetclinic.dto.PetCreateRequest;

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

    public Pet save(PetCreateRequest request) {

        Owner owner = ownerRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        Pet pet = new Pet();
        pet.setName(request.getName());
        pet.setSpecies(request.getSpecies());
        pet.setOwner(owner);

        return petRepository.save(pet);
    }

    public Pet update(Long id, PetCreateRequest request) {

        Pet existing = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        existing.setName(request.getName());
        existing.setSpecies(request.getSpecies());

        if (request.getOwnerId() != null) {
            Owner owner = ownerRepository.findById(request.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("Owner not found"));
            existing.setOwner(owner);
        }

        return petRepository.save(existing);
    }

    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
}
