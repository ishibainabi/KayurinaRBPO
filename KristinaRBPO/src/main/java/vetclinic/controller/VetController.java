package vetclinic.controller;

import org.springframework.web.bind.annotation.*;
import vetclinic.model.entity.Vet;
import vetclinic.service.VetService;

import java.util.List;

@RestController
@RequestMapping("/api/vets")
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping
    public List<Vet> getAll() {
        return vetService.findAll();
    }

    @GetMapping("/{id}")
    public Vet getById(@PathVariable Long id) {
        return vetService.findById(id);
    }

    @PostMapping
    public Vet create(@RequestBody Vet vet) {
        return vetService.save(vet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vetService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Vet update(@PathVariable Long id, @RequestBody Vet vet) {
        return vetService.update(id, vet);
    }
}
