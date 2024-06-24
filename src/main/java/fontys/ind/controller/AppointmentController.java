package fontys.ind.controller;

import fontys.ind.domain.response.appointment.GetAllAppointmentsResponse;
import fontys.ind.domain.response.appointment.GetAppointmentResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fontys.ind.business.AppointmentService;
import fontys.ind.domain.request.appointment.CreateAppointmentRequest;
import fontys.ind.domain.response.appointment.CreateAppointmentResponse;

@RestController
@RequestMapping("/appointments")
@AllArgsConstructor
// @CrossOrigin(origins = "http://localhost:5173")
@CrossOrigin(origins={"http://localhost:5173", "https://fitsphere-fontys.netlify.app"})
public class AppointmentController {
    private AppointmentService appointmentService;

    @PostMapping
    @RolesAllowed({"CLIENT"})
    public ResponseEntity<CreateAppointmentResponse> createAppointment(@RequestBody @Valid CreateAppointmentRequest request) {
        CreateAppointmentResponse response = appointmentService.createAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Integer id) {
        appointmentService.deleteAppointment((id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping
//    @RolesAllowed({"CLIENT", "TRAINER"})
    public ResponseEntity<GetAllAppointmentsResponse> getAllAppointments() {
        return ResponseEntity.ok((appointmentService.getAllAppointments()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetAllAppointmentsResponse> getAllAppointmentsByUser(@PathVariable(value = "id") final Integer id) {
        GetAllAppointmentsResponse response = appointmentService.getAllAppointmentsByUser(id);
        return ResponseEntity.ok(response);
    }

}
