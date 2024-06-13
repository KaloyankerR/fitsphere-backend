package fontys.ind.business.mappers;

import fontys.ind.domain.RoleEnum;
import fontys.ind.domain.request.user.CreateTrainerRequest;
import fontys.ind.domain.response.appointment.GetAppointmentResponse;
import fontys.ind.domain.response.user.GetTrainerResponse;
import fontys.ind.persistence.entity.AppointmentEntity;
import fontys.ind.persistence.entity.TrainerEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-07T19:59:46+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class TrainerMapperImpl implements TrainerMapper {

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public TrainerEntity fromRequestToEntity(CreateTrainerRequest request) {
        if ( request == null ) {
            return null;
        }

        TrainerEntity.TrainerEntityBuilder<?, ?> trainerEntity = TrainerEntity.builder();

        trainerEntity.firstName( request.getFirstName() );
        trainerEntity.lastName( request.getLastName() );
        trainerEntity.email( request.getEmail() );
        trainerEntity.password( request.getPassword() );
        if ( request.getRole() != null ) {
            trainerEntity.role( Enum.valueOf( RoleEnum.class, request.getRole() ) );
        }
        trainerEntity.bio( request.getBio() );
        trainerEntity.igLink( request.getIgLink() );

        return trainerEntity.build();
    }

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
        getTrainerResponse.appointmentList( appointmentEntityListToGetAppointmentResponseList( entity.getAppointments() ) );
        getTrainerResponse.rating( TrainerMapper.calculateMedianRating( entity.getRatings() ) );

        return getTrainerResponse.build();
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
