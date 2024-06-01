package fontys.ind.business.impl;

import fontys.ind.domain.response.workout.GetWorkoutInfoResponse;
import fontys.ind.persistence.entity.RatingEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class WorkoutServiceImpl implements WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final TrainerRepository trainerRepository;
    private final WorkoutMapper workoutMapper;

    @Override
    public CreateWorkoutResponse createWorkout(CreateWorkoutRequest request) {
        if (workoutRepository.existsByTitle(request.getTitle())) {
            throw new TitleAlreadyExistsException();
        }

        Optional<TrainerEntity> trainerEntityOptional = trainerRepository.findById(Long.valueOf(request.getTrainerId()));

        TrainerEntity trainerEntity = trainerEntityOptional.orElseThrow(() ->
                new EntityNotFoundException("Trainer with ID " + request.getTrainerId() + " not found."));

        WorkoutEntity newWorkout = WorkoutEntity.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .trainer(trainerEntity)
                .build();

        newWorkout = workoutRepository.save(newWorkout);

        return CreateWorkoutResponse.builder()
                .id(newWorkout.getId())
                .build();
    }

    @Override
    public void deleteWorkout(Integer id) {
        workoutRepository.deleteById(id);
    }

    @Override
    public GetWorkoutInfoResponse getWorkoutInfo(Integer id) {
        Optional<WorkoutEntity> workoutEntityOptional = workoutRepository.findById(id);

        if (workoutEntityOptional.isEmpty()){
            throw new EntityNotFoundException("Workout workout with ID " + id + " wasn't found!");
        }

        WorkoutEntity workoutEntity = workoutEntityOptional.get();
        TrainerEntity trainerEntity = workoutEntity.getTrainer();
//        List<RatingEntity> ratingEntities = ratingRepository.findAllByTrainerUserId(trainerEntity.getUserId());

        GetWorkoutInfoResponse workoutInfoResponse = new GetWorkoutInfoResponse();
        workoutInfoResponse.setWorkoutId(workoutEntity.getId());
        workoutInfoResponse.setTitle(workoutEntity.getTitle());
        workoutInfoResponse.setDescription(workoutEntity.getDescription());
        workoutInfoResponse.setStartTime(workoutEntity.getStartTime());
        workoutInfoResponse.setEndTime(workoutEntity.getEndTime());
        workoutInfoResponse.setTrainerId(trainerEntity.getUserId());
        workoutInfoResponse.setTrainerFirstName(trainerEntity.getFirstName());
        workoutInfoResponse.setTrainerLastName(trainerEntity.getLastName());
        workoutInfoResponse.setTrainerRating(calculateMedianRating(trainerEntity.getRatings()));

        return workoutInfoResponse;
    }

    public static double calculateMedianRating(List<RatingEntity> ratings) {
        List<Integer> sortedRatings = ratings.stream()
                .map(RatingEntity::getRating)
                .sorted()
                .toList();

        int size = sortedRatings.size();
        if (size == 0) {
            // throw new IllegalArgumentException("The list of ratings is empty");
            return 0;
        }

        if (size % 2 == 1) {
            // Odd number of ratings
            return sortedRatings.get(size / 2);
        } else {
            // Even number of ratings
            return (sortedRatings.get(size / 2 - 1) + sortedRatings.get(size / 2)) / 2.0;
        }
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

        List<WorkoutEntity> workoutsEntity = workoutRepository.findAllByTrainer(trainerEntity);
        List<GetWorkoutResponse> workouts = workoutsEntity.stream()
                .map(workoutMapper::fromEntityToResponse)
                .toList();

        return GetWorkoutsResponse.builder().workouts(workouts).build();
    }

    @Override
    public void updateWorkout(UpdateWorkoutRequest request) {
        Optional<WorkoutEntity> workoutEntityOptional = workoutRepository.findById(request.getId());

        if (workoutEntityOptional.isEmpty()) {
            throw new InvalidWorkoutException("WORKOUT_ID_INVALID");
        }

        WorkoutEntity workout = workoutEntityOptional.get();
        workout.setTitle(request.getTitle());
        workout.setDescription(request.getDescription());
        workout.setStartTime(request.getStartTime());
        workout.setEndTime(request.getEndTime());

        workoutRepository.save(workout);
    }

}
