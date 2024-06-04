package fontys.ind.business.mappers;

import fontys.ind.domain.response.user.GetUserResponse;
import fontys.ind.persistence.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-02T13:07:06+0200",
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
}
