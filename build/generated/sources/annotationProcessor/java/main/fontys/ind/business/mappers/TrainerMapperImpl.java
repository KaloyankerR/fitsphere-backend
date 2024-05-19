package fontys.ind.business.mappers;

import fontys.ind.domain.response.appointment.GetAppointmentResponse;
import fontys.ind.domain.response.rating.GetRatingResponse;
import fontys.ind.domain.response.user.GetTrainerResponse;
import fontys.ind.persistence.entity.AppointmentEntity;
import fontys.ind.persistence.entity.RatingEntity;
import fontys.ind.persistence.entity.TrainerEntity;
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
public class TrainerMapperImpl implements TrainerMapper {

    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private RatingMapper ratingMapper;

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
        getTrainerResponse.ratings( ratingEntityListToGetRatingResponseList( entity.getRatings() ) );

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

    protected List<GetRatingResponse> ratingEntityListToGetRatingResponseList(List<RatingEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<GetRatingResponse> list1 = new ArrayList<GetRatingResponse>( list.size() );
        for ( RatingEntity ratingEntity : list ) {
            list1.add( ratingMapper.fromEntityToResponse( ratingEntity ) );
        }

        return list1;
    }
}
