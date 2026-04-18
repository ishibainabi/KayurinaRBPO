package vetclinic.controller;

import org.springframework.web.bind.annotation.*;
import vetclinic.model.entity.Appointment;
import vetclinic.service.AppointmentService;
import vetclinic.dto.AppointmentCreateRequest;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Appointment> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Appointment create(@RequestBody AppointmentCreateRequest request) {
        return service.save(request);
    }

    @GetMapping("/{id}")
    public Appointment getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
