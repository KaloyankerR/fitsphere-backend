package fontys.ind.business.impl;

import fontys.ind.business.WorkoutService;
import fontys.ind.business.exception.InvalidWorkoutException;
import fontys.ind.business.exception.TitleAlreadyExistsException;
import fontys.ind.business.mappers.WorkoutMapper;
import fontys.ind.domain.request.workout.CreateWorkoutRequest;
import fontys.ind.domain.request.workout.UpdateWorkoutRequest;
import fontys.ind.domain.response.workout.CreateWorkoutResponse;
import fontys.ind.domain.response.workout.GetWorkoutResponse;
import fontys.ind.domain.response.workout.GetWorkoutsResponse;
import fontys.ind.persistence.TrainerRepository;
import fontys.ind.persistence.WorkoutRepository;
import fontys.ind.persistence.entity.TrainerEntity;
import fontys.ind.persistence.entity.WorkoutEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
