package vetclinic.controller;

import org.springframework.web.bind.annotation.*;
import vetclinic.model.entity.Treatment;
import vetclinic.service.TreatmentService;

import java.util.List;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentController {
    private final TreatmentService treatmentService;
    public TreatmentController(TreatmentService treatmentService) { this.treatmentService = treatmentService; }

    @GetMapping
    public List<Treatment> getAll() { return treatmentService.findAll(); }

    @GetMapping("/{id}")
    public Treatment getById(@PathVariable Long id) { return treatmentService.findById(id); }

    @PostMapping
    public Treatment create(@RequestBody Treatment treatment) { return treatmentService.save(treatment); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { treatmentService.deleteById(id); }
}