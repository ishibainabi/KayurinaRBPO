package vetclinic.controller;

import org.springframework.web.bind.annotation.*;
import vetclinic.model.entity.Appointment;
import vetclinic.service.AppointmentService;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    public AppointmentController(AppointmentService appointmentService) { this.appointmentService = appointmentService; }

    @GetMapping
    public List<Appointment> getAll() { return appointmentService.findAll(); }

    @GetMapping("/{id}")
    public Appointment getById(@PathVariable Long id) { return appointmentService.findById(id); }

    @PostMapping
    public Appointment create(@RequestBody Appointment appointment) { return appointmentService.save(appointment); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { appointmentService.deleteById(id); }
}