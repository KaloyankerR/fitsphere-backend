package s3.ind.domain.response.workout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import s3.ind.domain.User;
import s3.ind.domain.Workout;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllWorkoutsResponse {
    private List<Workout> workouts;
}
