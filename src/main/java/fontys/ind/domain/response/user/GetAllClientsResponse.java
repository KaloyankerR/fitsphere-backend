package fontys.ind.domain.response.user;

import fontys.ind.domain.response.ApiWrapperResponse;
import fontys.ind.domain.response.appointment.GetAppointmentResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GetAllClientsResponse extends ApiWrapperResponse {
    private List<GetClientResponse> clients;
}
