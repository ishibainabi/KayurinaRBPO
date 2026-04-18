package vetclinic.controller;

import org.springframework.web.bind.annotation.*;
import vetclinic.model.entity.Owner;
import vetclinic.service.OwnerService;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public List<Owner> getAll() {
        return ownerService.findAll();
    }

    @GetMapping("/{id}")
    public Owner getById(@PathVariable Long id) {
        return ownerService.findById(id);
    }

    @PostMapping
    public Owner create(@RequestBody Owner owner) {
        return ownerService.save(owner);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ownerService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Owner update(@PathVariable Long id, @RequestBody Owner owner) {
        return ownerService.update(id, owner);
    }
}
