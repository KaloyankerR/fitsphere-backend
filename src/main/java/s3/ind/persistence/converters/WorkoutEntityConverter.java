package s3.ind.persistence.converters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import s3.ind.domain.Workout;
import s3.ind.persistence.entity.WorkoutEntity;

@Service
@AllArgsConstructor
public class WorkoutEntityConverter {
    private final TrainerEntityConverter trainerConverter;

    public Workout fromEntity(WorkoutEntity entity){
        if (entity == null){
            return null;
        }

        return Workout.builder()
                .id(entity.getId())
                .trainer(trainerConverter.fromEntity(entity.getTrainer()))
                .title(entity.getTitle())
                .description(entity.getDescription())
                .build();
    }

    public WorkoutEntity toEntity(Workout workout){
        if (workout == null){
            return null;
        }

        return WorkoutEntity.builder()
                .id(workout.getId())
                .trainer(trainerConverter.toEntity(workout.getTrainer()))
                .title(workout.getTitle())
                .description(workout.getDescription())
                .build();
    }


}
