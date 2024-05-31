package fontys.ind.business.mappers;

import fontys.ind.domain.response.workout.GetWorkoutInfoResponse;
import fontys.ind.domain.response.workout.GetWorkoutResponse;
import fontys.ind.persistence.entity.TrainerEntity;
import fontys.ind.persistence.entity.WorkoutEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-27T12:48:03+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class WorkoutMapperImpl implements WorkoutMapper {

    @Override
    public GetWorkoutResponse fromEntityToResponse(WorkoutEntity entity) {
        if ( entity == null ) {
            return null;
        }

        GetWorkoutResponse.GetWorkoutResponseBuilder getWorkoutResponse = GetWorkoutResponse.builder();

        getWorkoutResponse.id( entity.getId() );
        getWorkoutResponse.title( entity.getTitle() );
        getWorkoutResponse.startTime( entity.getStartTime() );
        getWorkoutResponse.endTime( entity.getEndTime() );
        getWorkoutResponse.description( entity.getDescription() );

        return getWorkoutResponse.build();
    }

    @Override
    public GetWorkoutInfoResponse fromEntityToInfoResponse(WorkoutEntity entity) {
        if ( entity == null ) {
            return null;
        }

        GetWorkoutInfoResponse.GetWorkoutInfoResponseBuilder getWorkoutInfoResponse = GetWorkoutInfoResponse.builder();

        getWorkoutInfoResponse.workoutId( entity.getId() );
        getWorkoutInfoResponse.title( entity.getTitle() );
        getWorkoutInfoResponse.description( entity.getDescription() );
        getWorkoutInfoResponse.startTime( entity.getStartTime() );
        getWorkoutInfoResponse.endTime( entity.getEndTime() );
        getWorkoutInfoResponse.trainerId( entityTrainerUserId( entity ) );
        getWorkoutInfoResponse.trainerFirstName( entityTrainerFirstName( entity ) );
        getWorkoutInfoResponse.trainerLastName( entityTrainerLastName( entity ) );

        return getWorkoutInfoResponse.build();
    }

    private Integer entityTrainerUserId(WorkoutEntity workoutEntity) {
        if ( workoutEntity == null ) {
            return null;
        }
        TrainerEntity trainer = workoutEntity.getTrainer();
        if ( trainer == null ) {
            return null;
        }
        Integer userId = trainer.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private String entityTrainerFirstName(WorkoutEntity workoutEntity) {
        if ( workoutEntity == null ) {
            return null;
        }
        TrainerEntity trainer = workoutEntity.getTrainer();
        if ( trainer == null ) {
            return null;
        }
        String firstName = trainer.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String entityTrainerLastName(WorkoutEntity workoutEntity) {
        if ( workoutEntity == null ) {
            return null;
        }
        TrainerEntity trainer = workoutEntity.getTrainer();
        if ( trainer == null ) {
            return null;
        }
        String lastName = trainer.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }
}
