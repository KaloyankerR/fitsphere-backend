package s3.ind.business.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import s3.ind.business.WorkoutService;
import s3.ind.business.converters.TrainerConverter;
import s3.ind.business.converters.WorkoutConverter;
import s3.ind.business.exception.EmailAlreadyExistsException;
import s3.ind.business.exception.InvalidUserException;
import s3.ind.business.exception.InvalidWorkoutException;
import s3.ind.domain.Workout;
import s3.ind.domain.request.CreateUserRequest;
import s3.ind.domain.request.CreateWorkoutRequest;
import s3.ind.domain.request.UpdateUserRequest;
import s3.ind.domain.request.UpdateWorkoutRequest;
import s3.ind.domain.response.CreateUserResponse;
import s3.ind.domain.response.CreateWorkoutResponse;
import s3.ind.domain.response.WorkoutResponse;
import s3.ind.persistence.WorkoutRepository;
import s3.ind.persistence.entity.UserEntity;
import s3.ind.persistence.entity.WorkoutEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
// @Transactional check it out
public class WorkoutServiceImpl implements WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final WorkoutConverter workoutConverter = new WorkoutConverter(new TrainerConverter());

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
        WorkoutEntity newWorkout = WorkoutEntity.builder()
                // .trainer(request.getTrainerId())
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
    public List<Workout> getWorkouts() {
        List<WorkoutEntity> workouts = workoutRepository.findAll();

//        return workouts.stream()
//                .map(workoutMapper::toDomainObject)
//                .collect(Collectors.toList());
        return null;
//        return workouts.stream()
//                .collect(Collectors.toMap(
//                        WorkoutEntity::getWorkoutId,
//                        workoutMapper::toDomainObject
//                ));
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
