package vetclinic.controller;

import org.springframework.web.bind.annotation.*;
import vetclinic.model.entity.Treatment;
import vetclinic.service.TreatmentService;
import vetclinic.dto.TreatmentCreateRequest;

import java.util.List;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentController {

    private final TreatmentService service;

    public TreatmentController(TreatmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Treatment> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Treatment create(@RequestBody TreatmentCreateRequest request) {
        return service.save(request);
    }

    @GetMapping("/{id}")
    public Treatment getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
