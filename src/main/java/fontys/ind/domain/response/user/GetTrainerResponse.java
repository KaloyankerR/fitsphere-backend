package fontys.ind.domain.response.user;

import fontys.ind.domain.response.appointment.GetAppointmentResponse;
import fontys.ind.domain.response.rating.GetRatingResponse;
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
public class GetTrainerResponse extends GetUserResponse {
    private String bio;
    private String igLink;
    private List<GetAppointmentResponse> appointmentList;
    private Double rating;
}
