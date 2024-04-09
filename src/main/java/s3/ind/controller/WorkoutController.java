package s3.ind.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import s3.ind.business.WorkoutService;
import s3.ind.domain.Workout;
import s3.ind.domain.request.CreateWorkoutRequest;
import s3.ind.domain.response.UserResponse;
import s3.ind.domain.response.WorkoutResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workouts")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class WorkoutController {
    private WorkoutService workoutService;

    @GetMapping
    public ResponseEntity<List<Workout>> getAllWorkouts() {
        return ResponseEntity.ok(workoutService.getWorkouts());
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
    public ResponseEntity<Object> createWorkout(@RequestBody @Valid CreateWorkoutRequest request) {
        return null;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Integer id) {
        return null;
    }

}
