package s3.ind.business.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import s3.ind.business.WorkoutService;
import s3.ind.business.converters.TrainerConverter;
import s3.ind.business.converters.WorkoutConverter;
import s3.ind.business.exception.EmailAlreadyExistsException;
import s3.ind.business.exception.InvalidWorkoutException;
import s3.ind.domain.Workout;
import s3.ind.domain.request.CreateWorkoutRequest;
import s3.ind.domain.request.UpdateWorkoutRequest;
import s3.ind.domain.response.workout.CreateWorkoutResponse;
import s3.ind.domain.response.workout.GetAllWorkoutsResponse;
import s3.ind.domain.response.WorkoutResponse;
import s3.ind.persistence.TrainerRepository;
import s3.ind.persistence.WorkoutRepository;
import s3.ind.persistence.converters.TrainerEntityConverter;
import s3.ind.persistence.converters.WorkoutEntityConverter;
import s3.ind.persistence.entity.TrainerEntity;
import s3.ind.persistence.entity.WorkoutEntity;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
// @Transactional check it out
public class WorkoutServiceImpl implements WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final TrainerRepository trainerRepository;

    private final WorkoutConverter workoutConverter = new WorkoutConverter(new TrainerConverter());
    private final WorkoutEntityConverter workoutEntityConverter = new WorkoutEntityConverter(new TrainerEntityConverter());

    // CREATE
    @Override
    @Transactional
    public CreateWorkoutResponse createWorkout(CreateWorkoutRequest request) {
        if (workoutRepository.existsByTitle(request.getTitle())) {
            // TODO: make one general exception where you can add the text in the cases
            throw new EmailAlreadyExistsException();
        }

        WorkoutEntity newWorkout = saveNewWorkout(request);

        return CreateWorkoutResponse.builder()
                .id(newWorkout.getId())
                .build();
    }

    private WorkoutEntity saveNewWorkout(CreateWorkoutRequest request) {
        Optional<TrainerEntity> trainerEntityOptional = trainerRepository.findById(Long.valueOf(request.getTrainerId()));

        TrainerEntity trainerEntity = trainerEntityOptional.orElseThrow(() ->
                new EntityNotFoundException("Trainer with ID " + request.getTrainerId() + " not found."));

        WorkoutEntity newWorkout = WorkoutEntity.builder()
                .trainer(trainerEntity)
                .title(request.getTitle())
                .description(request.getDescription())
                .build();

        return workoutRepository.save(newWorkout);
    }

    // DELETE
    @Override
    @Transactional
    public void deleteWorkout(Integer id) {
        workoutRepository.deleteById(id);
    }

    @Override
    public WorkoutResponse getWorkout(Integer id) {
        return null;
    }

    @Override
    public GetAllWorkoutsResponse getAllWorkouts() {
        List<WorkoutEntity> workoutsEntity = workoutRepository.findAll();
        List<Workout> workouts = workoutsEntity.stream()
                .map(workoutEntityConverter::fromEntity)
                .toList();

        final GetAllWorkoutsResponse response = new GetAllWorkoutsResponse();
        response.setWorkouts(workouts);

        return response;
    }

    // UPDATE
    @Override
    @Transactional
    public void updateWorkout(UpdateWorkoutRequest request) {
        Optional<WorkoutEntity> workoutEntityOptional = workoutRepository.findById(request.getId());

        if (workoutEntityOptional.isEmpty()) {
            throw new InvalidWorkoutException("WORKOUT_ID_INVALID");
        }

        WorkoutEntity workout = workoutEntityOptional.get();
        updateFields(request, workout);
    }

    private void updateFields(UpdateWorkoutRequest request, WorkoutEntity workout) {
        workout.setTitle(request.getTitle());
        workout.setDescription(request.getDescription());

        workoutRepository.save(workout);
    }
}
