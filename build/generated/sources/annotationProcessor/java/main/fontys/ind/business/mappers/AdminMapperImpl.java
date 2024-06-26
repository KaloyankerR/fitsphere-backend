package fontys.ind.business.mappers;

import fontys.ind.domain.RoleEnum;
import fontys.ind.domain.request.user.CreateUserRequest;
import fontys.ind.persistence.entity.AdminEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-02T13:07:06+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class AdminMapperImpl implements AdminMapper {

    @Override
    public AdminEntity fromRequestToEntity(CreateUserRequest request) {
        if ( request == null ) {
            return null;
        }

        AdminEntity.AdminEntityBuilder<?, ?> adminEntity = AdminEntity.builder();

        adminEntity.firstName( request.getFirstName() );
        adminEntity.lastName( request.getLastName() );
        adminEntity.email( request.getEmail() );
        adminEntity.password( request.getPassword() );
        if ( request.getRole() != null ) {
            adminEntity.role( Enum.valueOf( RoleEnum.class, request.getRole() ) );
        }

        return adminEntity.build();
    }
}
