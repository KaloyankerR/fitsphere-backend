package fontys.ind.business.mappers;

import fontys.ind.domain.response.appointment.GetAppointmentResponse;
import fontys.ind.domain.response.user.GetTrainerResponse;
import fontys.ind.persistence.entity.AppointmentEntity;
import fontys.ind.persistence.entity.TrainerEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-09T13:35:56+0200",
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
        getTrainerResponse.appointmentList( appointmentEntityListToGetAppointmentResponseList( entity.getAppointments() ) );

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

    protected GetAppointmentResponse appointmentEntityToGetAppointmentResponse(AppointmentEntity appointmentEntity) {
        if ( appointmentEntity == null ) {
            return null;
        }

        GetAppointmentResponse.GetAppointmentResponseBuilder getAppointmentResponse = GetAppointmentResponse.builder();

        getAppointmentResponse.id( appointmentEntity.getId() );
        getAppointmentResponse.startTime( appointmentEntity.getStartTime() );
        getAppointmentResponse.endTime( appointmentEntity.getEndTime() );

        return getAppointmentResponse.build();
    }

    protected List<GetAppointmentResponse> appointmentEntityListToGetAppointmentResponseList(List<AppointmentEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<GetAppointmentResponse> list1 = new ArrayList<GetAppointmentResponse>( list.size() );
        for ( AppointmentEntity appointmentEntity : list ) {
            list1.add( appointmentEntityToGetAppointmentResponse( appointmentEntity ) );
        }

        return list1;
    }
}
