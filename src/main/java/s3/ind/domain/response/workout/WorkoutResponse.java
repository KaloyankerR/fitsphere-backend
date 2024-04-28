package s3.ind.domain.response.workout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutResponse {
    private Integer id;
    private Integer trainerId;
    private String title;
    private String description;
}
