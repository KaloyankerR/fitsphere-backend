package fontys.ind.business.mappers;

import fontys.ind.domain.response.rating.GetRatingResponse;
import fontys.ind.persistence.entity.RatingEntity;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-12T20:11:36+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class RatingMapperImpl implements RatingMapper {

    @Autowired
    private TrainerMapper trainerMapper;
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public GetRatingResponse fromEntityToResponse(RatingEntity entity) {
        if ( entity == null ) {
            return null;
        }

        GetRatingResponse.GetRatingResponseBuilder getRatingResponse = GetRatingResponse.builder();

        if ( entity.getId() != null ) {
            getRatingResponse.id( entity.getId().intValue() );
        }
        getRatingResponse.comment( entity.getComment() );
        getRatingResponse.trainer( trainerMapper.fromEntityToResponse( entity.getTrainer() ) );
        getRatingResponse.client( clientMapper.fromEntityToResponse( entity.getClient() ) );

        return getRatingResponse.build();
    }
}
