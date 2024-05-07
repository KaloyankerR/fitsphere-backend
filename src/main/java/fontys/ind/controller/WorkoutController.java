package fontys.ind.controller;

import fontys.ind.business.WorkoutService;
import fontys.ind.domain.request.workout.CreateWorkoutRequest;
import fontys.ind.domain.response.workout.CreateWorkoutResponse;
import fontys.ind.domain.response.workout.GetWorkoutResponse;
import fontys.ind.domain.response.workout.GetWorkoutsResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workouts")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class WorkoutController {
    private WorkoutService workoutService;

    @GetMapping
    public ResponseEntity<GetWorkoutsResponse> getAllWorkouts() {
        return ResponseEntity.ok(workoutService.getAllWorkouts());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetWorkoutResponse> getWorkout(@PathVariable(value = "id") final Integer id) {
        return null;
    }

    @GetMapping("/trainer/{id}")
    public ResponseEntity<GetWorkoutsResponse> getTrainerWorkouts(@PathVariable(value = "id") final Integer id) {
        return ResponseEntity.ok(workoutService.getTrainerWorkouts(id));
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
