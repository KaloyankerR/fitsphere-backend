package fontys.ind.business.mappers;

import fontys.ind.domain.response.workout.GetWorkoutResponse;
import fontys.ind.persistence.entity.WorkoutEntity;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-07T12:25:50+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class WorkoutMapperImpl implements WorkoutMapper {

    @Autowired
    private TrainerMapper trainerMapper;

    @Override
    public GetWorkoutResponse fromEntityToResponse(WorkoutEntity entity) {
        if ( entity == null ) {
            return null;
        }

        GetWorkoutResponse.GetWorkoutResponseBuilder getWorkoutResponse = GetWorkoutResponse.builder();

        getWorkoutResponse.id( entity.getId() );
        getWorkoutResponse.trainer( trainerMapper.fromEntityToResponse( entity.getTrainer() ) );
        getWorkoutResponse.title( entity.getTitle() );
        getWorkoutResponse.description( entity.getDescription() );

        return getWorkoutResponse.build();
    }
}
