package fontys.ind.business.mappers;

import fontys.ind.domain.response.user.GetUserResponse;
import fontys.ind.persistence.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-07T12:25:51+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
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
