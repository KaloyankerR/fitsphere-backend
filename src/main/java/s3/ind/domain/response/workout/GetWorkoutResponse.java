package s3.ind.domain.response.workout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import s3.ind.domain.response.user.GetTrainerResponse;

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
