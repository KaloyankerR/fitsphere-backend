package s3.ind.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s3.ind.business.AppointmentService;
import s3.ind.domain.request.appointment.CreateAppointmentRequest;
import s3.ind.domain.response.appointment.CreateAppointmentResponse;

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
}
