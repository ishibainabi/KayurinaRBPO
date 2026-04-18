package vetclinic.controller;

import org.springframework.web.bind.annotation.*;
import vetclinic.dto.AppointmentCreateRequest;
import vetclinic.model.entity.Appointment;
import vetclinic.service.AppointmentService;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public List<Appointment> getAll() {
        return appointmentService.findAll();
    }

    @GetMapping("/{id}")
    public Appointment getById(@PathVariable Long id) {
        return appointmentService.findById(id);
    }

    @PostMapping
    public Appointment create(@RequestBody AppointmentCreateRequest request) {
        return appointmentService.save(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        appointmentService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Appointment update(@PathVariable Long id,
                              @RequestBody AppointmentCreateRequest request) {
        return appointmentService.update(id, request);
    }
}
