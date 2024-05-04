package s3.ind.business;

import s3.ind.domain.request.workout.CreateWorkoutRequest;
import s3.ind.domain.request.workout.UpdateWorkoutRequest;
import s3.ind.domain.response.workout.CreateWorkoutResponse;
import s3.ind.domain.response.workout.GetWorkoutResponse;
import s3.ind.domain.response.workout.GetWorkoutsResponse;

public interface WorkoutService {
    CreateWorkoutResponse createWorkout(CreateWorkoutRequest request);

    void deleteWorkout(Integer id);

    GetWorkoutResponse getWorkout(Integer id);

    GetWorkoutsResponse getAllWorkouts();
    GetWorkoutsResponse getTrainerWorkouts(Integer id);

    void updateWorkout(UpdateWorkoutRequest request);
}
