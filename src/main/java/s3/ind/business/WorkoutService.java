package s3.ind.business;

import s3.ind.domain.Workout;
import s3.ind.domain.request.CreateWorkoutRequest;
import s3.ind.domain.request.UpdateWorkoutRequest;
import s3.ind.domain.response.CreateWorkoutResponse;
import s3.ind.domain.response.WorkoutResponse;

import java.util.List;

public interface WorkoutService {
    CreateWorkoutResponse createWorkout(CreateWorkoutRequest request);

    void deleteWorkout(Integer id);

    WorkoutResponse getWorkout(Integer id);

    List<Workout> getWorkouts();

    void updateWorkout(UpdateWorkoutRequest request);
}
