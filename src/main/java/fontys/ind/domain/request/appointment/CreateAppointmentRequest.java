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
}
