package s3.ind.business.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import s3.ind.business.WorkoutService;
import s3.ind.business.converters.TrainerConverter;
import s3.ind.business.converters.WorkoutConverter;
import s3.ind.domain.Workout;
import s3.ind.domain.request.CreateWorkoutRequest;
import s3.ind.domain.request.UpdateWorkoutRequest;
import s3.ind.domain.response.WorkoutResponse;
import s3.ind.persistence.WorkoutRepository;
import s3.ind.persistence.entity.WorkoutEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
// @Transactional check it out
public class WorkoutServiceImpl implements WorkoutService {
    private final WorkoutRepository workoutRepository;
    // private final WorkoutMapper workoutMapper;
    private final WorkoutConverter workoutConverter = new WorkoutConverter(new TrainerConverter());

    @Override
    public boolean createWorkout(Workout workout) {
        return false;
        // return workoutConverter.fromEntity(workoutRepository.save(converter.toEntity(pet)));
    }

    @Override
    public boolean deleteWorkout(Integer id) {
        return false;
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

    @Override
    public boolean updateWorkout(UpdateWorkoutRequest request) {
        return false;
    }
}
