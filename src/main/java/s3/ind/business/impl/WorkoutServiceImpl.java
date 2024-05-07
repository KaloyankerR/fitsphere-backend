package s3.ind.business.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import s3.ind.business.WorkoutService;
import s3.ind.business.exception.EmailAlreadyExistsException;
import s3.ind.business.exception.InvalidWorkoutException;
import s3.ind.business.exception.TitleAlreadyExistsException;
import s3.ind.business.mappers.WorkoutMapper;
import s3.ind.domain.request.workout.CreateWorkoutRequest;
import s3.ind.domain.request.workout.UpdateWorkoutRequest;
import s3.ind.domain.response.workout.CreateWorkoutResponse;
import s3.ind.domain.response.workout.GetWorkoutResponse;
import s3.ind.domain.response.workout.GetWorkoutsResponse;
import s3.ind.persistence.TrainerRepository;
import s3.ind.persistence.WorkoutRepository;
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
    private final WorkoutMapper workoutMapper;


    // CREATE
    @Override
    @Transactional
    public CreateWorkoutResponse createWorkout(CreateWorkoutRequest request) {
        if (workoutRepository.existsByTitle(request.getTitle())) {
            // TODO: make one general exception where you can add the text in the cases
            throw new TitleAlreadyExistsException();
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

    // GET
    @Override
    public GetWorkoutResponse getWorkout(Integer id) {
        return null;
    }

    @Override
    public GetWorkoutsResponse getAllWorkouts() {
        List<WorkoutEntity> workoutsEntity = workoutRepository.findAll();

        List<GetWorkoutResponse> workouts = workoutsEntity.stream()
                .map(workoutMapper::fromEntityToResponse)
                .toList();

        final GetWorkoutsResponse response = new GetWorkoutsResponse();
        response.setWorkouts(workouts);

        return response;
    }

    @Override
    public GetWorkoutsResponse getTrainerWorkouts(Integer id) {
        TrainerEntity trainerEntity = TrainerEntity.builder().userId(id).build();
//        Trainer Entity trainerEntity = trainerRepository.findById(Long.valueOf(id))
//                .orElseThrow(() -> new NotFoundException("Trainer not found with ID: " + id));

        List<WorkoutEntity> workoutsEntity = workoutRepository.findAllByTrainer(trainerEntity);
        List<GetWorkoutResponse> workouts = workoutsEntity.stream()
                .map(workoutMapper::fromEntityToResponse)
                .toList();

        return GetWorkoutsResponse.builder().workouts(workouts).build();
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
