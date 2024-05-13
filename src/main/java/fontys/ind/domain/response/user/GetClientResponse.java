package fontys.ind.domain.response.user;

import fontys.ind.domain.response.appointment.GetAppointmentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GetClientResponse extends GetUserResponse {
    private List<GetAppointmentResponse> appointmentList;
}