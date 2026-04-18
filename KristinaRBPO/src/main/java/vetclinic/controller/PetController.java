package vetclinic.controller;

import org.springframework.web.bind.annotation.*;
import vetclinic.model.entity.Pet;
import vetclinic.service.PetService;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public List<Pet> getAll() {
        return petService.findAll();
    }

    @GetMapping("/{id}")
    public Pet getById(@PathVariable Long id) {
        return petService.findById(id);
    }

    @PostMapping
    public Pet create(@RequestBody Pet pet) {
        return petService.save(pet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        petService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Pet update(@PathVariable Long id, @RequestBody Pet pet) {
        return petService.update(id, pet);
    }
}
