package s3.ind.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s3.ind.business.WorkoutService;
import s3.ind.domain.Workout;
import s3.ind.domain.request.workout.CreateWorkoutRequest;
import s3.ind.domain.response.workout.CreateWorkoutResponse;
import s3.ind.domain.response.workout.GetAllWorkoutsResponse;
import s3.ind.domain.response.workout.WorkoutResponse;

@RestController
@RequestMapping("/workouts")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class WorkoutController {
    private WorkoutService workoutService;

    @GetMapping
    public ResponseEntity<GetAllWorkoutsResponse> getAllWorkouts() {
        return ResponseEntity.ok(workoutService.getAllWorkouts());
    }

    @GetMapping("{id}")
    public ResponseEntity<Workout> getWorkout(@PathVariable(value = "id") final Integer id) {
        return null;
    }

    @GetMapping("/trainer/{id}")
    public ResponseEntity<WorkoutResponse> getTrainerWorkouts(@PathVariable(value = "id") final Integer id) {
        return null;
    }

    @PostMapping()
    public ResponseEntity<CreateWorkoutResponse> createWorkout(@RequestBody @Valid CreateWorkoutRequest request) {
        CreateWorkoutResponse response = workoutService.createWorkout(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Integer id) {
        return null;
    }

}
