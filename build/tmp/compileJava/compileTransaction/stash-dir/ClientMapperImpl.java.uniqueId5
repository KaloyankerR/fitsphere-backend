package fontys.ind.business.mappers;

import fontys.ind.domain.RoleEnum;
import fontys.ind.domain.request.user.CreateUserRequest;
import fontys.ind.domain.response.appointment.GetAppointmentResponse;
import fontys.ind.domain.response.user.GetClientResponse;
import fontys.ind.persistence.entity.AppointmentEntity;
import fontys.ind.persistence.entity.ClientEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-19T15:20:18+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Autowired
    private AppointmentMapper appointmentMapper;

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

    @Override
    public GetClientResponse fromEntityToResponse(ClientEntity entity) {
        if ( entity == null ) {
            return null;
        }

        GetClientResponse.GetClientResponseBuilder<?, ?> getClientResponse = GetClientResponse.builder();

        getClientResponse.id( entity.getUserId() );
        getClientResponse.firstName( entity.getFirstName() );
        getClientResponse.lastName( entity.getLastName() );
        getClientResponse.email( entity.getEmail() );
        getClientResponse.password( entity.getPassword() );
        getClientResponse.role( entity.getRole() );
        getClientResponse.appointmentList( appointmentEntityListToGetAppointmentResponseList( entity.getAppointments() ) );

        return getClientResponse.build();
    }

    protected List<GetAppointmentResponse> appointmentEntityListToGetAppointmentResponseList(List<AppointmentEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<GetAppointmentResponse> list1 = new ArrayList<GetAppointmentResponse>( list.size() );
        for ( AppointmentEntity appointmentEntity : list ) {
            list1.add( appointmentMapper.fromEntityToResponse( appointmentEntity ) );
        }

        return list1;
    }
}
