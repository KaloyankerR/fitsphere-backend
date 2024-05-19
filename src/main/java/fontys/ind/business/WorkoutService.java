package fontys.ind.business;

import fontys.ind.domain.request.workout.CreateWorkoutRequest;
import fontys.ind.domain.request.workout.UpdateWorkoutRequest;
import fontys.ind.domain.response.workout.CreateWorkoutResponse;
import fontys.ind.domain.response.workout.GetWorkoutInfoResponse;
import fontys.ind.domain.response.workout.GetWorkoutResponse;
import fontys.ind.domain.response.workout.GetWorkoutsResponse;

import java.util.List;

public interface WorkoutService {
    CreateWorkoutResponse createWorkout(CreateWorkoutRequest request);

    void deleteWorkout(Integer id);

    GetWorkoutResponse getWorkout(Integer id);
    GetWorkoutInfoResponse getWorkoutInfo(Integer id);

    GetWorkoutsResponse getAllWorkouts();
    GetWorkoutsResponse getTrainerWorkouts(Integer id);

    void updateWorkout(UpdateWorkoutRequest request);
}
