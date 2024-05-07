package fontys.ind.domain.request.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppointmentRequest {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer workoutId;
    private Integer trainerId;
    private Integer clientId;

//    public CreateAppointmentRequest() {
//        // Set default values here
//        this.startTime = LocalDateTime.now();
//        this.endTime = LocalDateTime.now().plusMinutes(30); // Default end time is one hour from start time
//        this.workoutId = 0; // Default workout ID
//        this.trainerId = 0; // Default trainer ID
//        this.clientId = 0; // Default client ID
//    }
}
