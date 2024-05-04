package s3.ind.business.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import s3.ind.domain.response.user.GetUserResponse;
import s3.ind.persistence.entity.UserEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-04T23:30:20+0200",
    comments = "version: 1.5.0.Beta2, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public GetUserResponse fromEntityToResponse(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        GetUserResponse.GetUserResponseBuilder<?, ?> getUserResponse = GetUserResponse.builder();

        getUserResponse.id( entity.getUserId() );
        getUserResponse.firstName( entity.getFirstName() );
        getUserResponse.lastName( entity.getLastName() );
        getUserResponse.email( entity.getEmail() );
        getUserResponse.password( entity.getPassword() );
        getUserResponse.role( entity.getRole() );

        return getUserResponse.build();
    }

    @Override
    public UserEntity fromResponseToEntity(GetUserResponse response) {
        if ( response == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder<?, ?> userEntity = UserEntity.builder();

        userEntity.userId( response.getId() );
        userEntity.firstName( response.getFirstName() );
        userEntity.lastName( response.getLastName() );
        userEntity.email( response.getEmail() );
        userEntity.password( response.getPassword() );
        userEntity.role( response.getRole() );

        return userEntity.build();
    }
}