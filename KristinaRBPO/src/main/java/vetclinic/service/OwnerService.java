package vetclinic.service;

import org.springframework.stereotype.Service;
import vetclinic.model.entity.Owner;
import vetclinic.repository.OwnerRepository;

import java.util.List;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    public OwnerService(OwnerRepository ownerRepository) { this.ownerRepository = ownerRepository; }

    public List<Owner> findAll() { return ownerRepository.findAll(); }
    public Owner findById(Long id) { return ownerRepository.findById(id).orElse(null); }
    public Owner save(Owner owner) { return ownerRepository.save(owner); }
    public void deleteById(Long id) { ownerRepository.deleteById(id); }
}