package fontys.ind.business;

import fontys.ind.domain.request.workout.CreateWorkoutRequest;
import fontys.ind.domain.request.workout.UpdateWorkoutRequest;
import fontys.ind.domain.response.workout.CreateWorkoutResponse;
import fontys.ind.domain.response.workout.GetWorkoutResponse;
import fontys.ind.domain.response.workout.GetWorkoutsResponse;

public interface WorkoutService {
    CreateWorkoutResponse createWorkout(CreateWorkoutRequest request);

    void deleteWorkout(Integer id);

    GetWorkoutResponse getWorkout(Integer id);

    GetWorkoutsResponse getAllWorkouts();
    GetWorkoutsResponse getTrainerWorkouts(Integer id);

    void updateWorkout(UpdateWorkoutRequest request);
}
