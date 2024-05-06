package s3.ind.domain.response.appointment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAppointmentResponse {
    private Integer id;
}
