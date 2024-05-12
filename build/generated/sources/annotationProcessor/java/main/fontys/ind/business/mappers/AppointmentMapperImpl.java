package fontys.ind.business.mappers;

import fontys.ind.domain.response.appointment.GetAppointmentResponse;
import fontys.ind.persistence.entity.AppointmentEntity;
import fontys.ind.persistence.entity.ClientEntity;
import fontys.ind.persistence.entity.TrainerEntity;
import fontys.ind.persistence.entity.WorkoutEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-12T23:52:01+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public GetAppointmentResponse fromEntityToResponse(AppointmentEntity entity) {
        if ( entity == null ) {
            return null;
        }

        GetAppointmentResponse.GetAppointmentResponseBuilder getAppointmentResponse = GetAppointmentResponse.builder();

        getAppointmentResponse.id( entity.getId() );
        getAppointmentResponse.startTime( entity.getStartTime() );
        getAppointmentResponse.endTime( entity.getEndTime() );
        getAppointmentResponse.workoutId( entityWorkoutId( entity ) );
        getAppointmentResponse.workoutTitle( entityWorkoutTitle( entity ) );
        getAppointmentResponse.trainerId( entityTrainerUserId( entity ) );
        getAppointmentResponse.clientId( entityClientUserId( entity ) );

        return getAppointmentResponse.build();
    }

    private Integer entityWorkoutId(AppointmentEntity appointmentEntity) {
        if ( appointmentEntity == null ) {
            return null;
        }
        WorkoutEntity workout = appointmentEntity.getWorkout();
        if ( workout == null ) {
            return null;
        }
        Integer id = workout.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityWorkoutTitle(AppointmentEntity appointmentEntity) {
        if ( appointmentEntity == null ) {
            return null;
        }
        WorkoutEntity workout = appointmentEntity.getWorkout();
        if ( workout == null ) {
            return null;
        }
        String title = workout.getTitle();
        if ( title == null ) {
            return null;
        }
        return title;
    }

    private Integer entityTrainerUserId(AppointmentEntity appointmentEntity) {
        if ( appointmentEntity == null ) {
            return null;
        }
        TrainerEntity trainer = appointmentEntity.getTrainer();
        if ( trainer == null ) {
            return null;
        }
        Integer userId = trainer.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private Integer entityClientUserId(AppointmentEntity appointmentEntity) {
        if ( appointmentEntity == null ) {
            return null;
        }
        ClientEntity client = appointmentEntity.getClient();
        if ( client == null ) {
            return null;
        }
        Integer userId = client.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }
}
