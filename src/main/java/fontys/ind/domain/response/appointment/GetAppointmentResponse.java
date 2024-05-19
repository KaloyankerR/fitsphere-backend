package fontys.ind.domain.response.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAppointmentResponse {
    private Integer id;
    private Integer workoutId;
    private String workoutTitle;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer trainerId;
    private String trainerFirstName;
    private String trainerLastName;
    private Integer clientId;
    private String clientFirstName;
    private String clientLastName;
}
