package fontys.ind.domain.response.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllAppointmentsResponse {
    private List<GetAppointmentResponse> appointments;
}
