package fontys.ind.business.mappers;

import fontys.ind.domain.response.rating.GetRatingResponse;
import fontys.ind.persistence.entity.RatingEntity;
import fontys.ind.persistence.entity.TrainerEntity;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-02T13:07:06+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class RatingMapperImpl implements RatingMapper {

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
        getRatingResponse.rating( entity.getRating() );
        getRatingResponse.comment( entity.getComment() );
        getRatingResponse.trainerId( entityTrainerUserId( entity ) );
        getRatingResponse.client( clientMapper.fromEntityToResponse( entity.getClient() ) );

        return getRatingResponse.build();
    }

    private Integer entityTrainerUserId(RatingEntity ratingEntity) {
        if ( ratingEntity == null ) {
            return null;
        }
        TrainerEntity trainer = ratingEntity.getTrainer();
        if ( trainer == null ) {
            return null;
        }
        Integer userId = trainer.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }
}
