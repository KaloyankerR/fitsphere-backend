package fontys.ind.domain.response.rating;

import fontys.ind.domain.response.user.GetClientResponse;
import fontys.ind.domain.response.user.GetTrainerResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetRatingResponse {
    private Integer id;
    private Integer rating;
    private String comment;
    private GetTrainerResponse trainer;
    private GetClientResponse client;
}
