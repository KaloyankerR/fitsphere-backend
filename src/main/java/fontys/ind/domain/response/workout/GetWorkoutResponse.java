package fontys.ind.domain.response.workout;

import fontys.ind.domain.response.user.GetTrainerResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetWorkoutResponse {
    private Integer id;
    private GetTrainerResponse trainer;
    private String title;
    private String description;
}
