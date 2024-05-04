package s3.ind.business.mappers;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import s3.ind.domain.response.workout.GetWorkoutResponse;
import s3.ind.persistence.entity.WorkoutEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-05T00:36:19+0200",
    comments = "version: 1.5.0.Beta2, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
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
