package s3.ind.business.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import s3.ind.domain.response.user.GetTrainerResponse;
import s3.ind.persistence.entity.TrainerEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-05T00:29:51+0200",
    comments = "version: 1.5.0.Beta2, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class TrainerMapperImpl implements TrainerMapper {

    @Override
    public GetTrainerResponse fromEntityToResponse(TrainerEntity entity) {
        if ( entity == null ) {
            return null;
        }

        GetTrainerResponse.GetTrainerResponseBuilder<?, ?> getTrainerResponse = GetTrainerResponse.builder();

        getTrainerResponse.id( entity.getUserId() );
        getTrainerResponse.firstName( entity.getFirstName() );
        getTrainerResponse.lastName( entity.getLastName() );
        getTrainerResponse.email( entity.getEmail() );
        getTrainerResponse.password( entity.getPassword() );
        getTrainerResponse.role( entity.getRole() );
        getTrainerResponse.bio( entity.getBio() );
        getTrainerResponse.igLink( entity.getIgLink() );
        getTrainerResponse.image( entity.getProfileImageUrl() );

        return getTrainerResponse.build();
    }

    @Override
    public TrainerEntity fromResponseToEntity(GetTrainerResponse response) {
        if ( response == null ) {
            return null;
        }

        TrainerEntity.TrainerEntityBuilder<?, ?> trainerEntity = TrainerEntity.builder();

        trainerEntity.userId( response.getId() );
        trainerEntity.firstName( response.getFirstName() );
        trainerEntity.lastName( response.getLastName() );
        trainerEntity.email( response.getEmail() );
        trainerEntity.password( response.getPassword() );
        trainerEntity.role( response.getRole() );
        trainerEntity.bio( response.getBio() );
        trainerEntity.igLink( response.getIgLink() );
        trainerEntity.profileImageUrl( response.getImage() );

        return trainerEntity.build();
    }
}
