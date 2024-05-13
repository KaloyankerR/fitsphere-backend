package fontys.ind.controller;

import fontys.ind.domain.request.appointment.UpdateAppointmentRequest;
import fontys.ind.domain.response.appointment.GetAllAppointmentsResponse;
import fontys.ind.domain.response.appointment.GetAppointmentResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fontys.ind.business.AppointmentService;
import fontys.ind.domain.request.appointment.CreateAppointmentRequest;
import fontys.ind.domain.response.appointment.CreateAppointmentResponse;

import java.io.InvalidClassException;

@RestController
@RequestMapping("/appointments")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AppointmentController {
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<CreateAppointmentResponse> createAppointment(@RequestBody @Valid CreateAppointmentRequest request){
        CreateAppointmentResponse response = appointmentService.createAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<GetAppointmentResponse> updateAppointment(@PathVariable("id") Integer id, @RequestBody UpdateAppointmentRequest request) throws InvalidClassException {
        request.setId(id);
        appointmentService.updateAppointment(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Integer id) {
        appointmentService.deleteAppointment((id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<GetAllAppointmentsResponse> getAllAppointments() {
        return ResponseEntity.ok((appointmentService.getAllAppointments()));
    }

}