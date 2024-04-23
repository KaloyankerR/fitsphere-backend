package s3.ind.business;

import s3.ind.domain.request.CreateWorkoutRequest;
import s3.ind.domain.request.UpdateWorkoutRequest;
import s3.ind.domain.response.workout.CreateWorkoutResponse;
import s3.ind.domain.response.workout.GetAllWorkoutsResponse;
import s3.ind.domain.response.WorkoutResponse;

public interface WorkoutService {
    CreateWorkoutResponse createWorkout(CreateWorkoutRequest request);

    void deleteWorkout(Integer id);

    WorkoutResponse getWorkout(Integer id);

    GetAllWorkoutsResponse getAllWorkouts();

    void updateWorkout(UpdateWorkoutRequest request);
}
