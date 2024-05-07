package fontys.ind.business.mappers;

import fontys.ind.domain.RoleEnum;
import fontys.ind.domain.request.user.CreateUserRequest;
import fontys.ind.persistence.entity.ClientEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-07T12:25:51+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientEntity fromRequestToEntity(CreateUserRequest request) {
        if ( request == null ) {
            return null;
        }

        ClientEntity.ClientEntityBuilder<?, ?> clientEntity = ClientEntity.builder();

        clientEntity.firstName( request.getFirstName() );
        clientEntity.lastName( request.getLastName() );
        clientEntity.email( request.getEmail() );
        clientEntity.password( request.getPassword() );
        if ( request.getRole() != null ) {
            clientEntity.role( Enum.valueOf( RoleEnum.class, request.getRole() ) );
        }

        return clientEntity.build();
    }
}
