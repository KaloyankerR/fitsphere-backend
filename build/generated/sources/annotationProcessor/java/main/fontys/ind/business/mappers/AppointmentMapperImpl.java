package fontys.ind.business.mappers;

import fontys.ind.domain.response.appointment.GetAppointmentResponse;
import fontys.ind.persistence.entity.AppointmentEntity;
import fontys.ind.persistence.entity.ClientEntity;
import fontys.ind.persistence.entity.TrainerEntity;
import fontys.ind.persistence.entity.WorkoutEntity;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-19T15:20:18+0200",
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
        getAppointmentResponse.workoutId( entityWorkoutId( entity ) );
        getAppointmentResponse.workoutTitle( entityWorkoutTitle( entity ) );
        getAppointmentResponse.startTime( entityWorkoutStartTime( entity ) );
        getAppointmentResponse.endTime( entityWorkoutEndTime( entity ) );
        getAppointmentResponse.trainerId( entityTrainerUserId( entity ) );
        getAppointmentResponse.trainerFirstName( entityTrainerFirstName( entity ) );
        getAppointmentResponse.trainerLastName( entityTrainerLastName( entity ) );
        getAppointmentResponse.clientId( entityClientUserId( entity ) );
        getAppointmentResponse.clientFirstName( entityClientFirstName( entity ) );
        getAppointmentResponse.clientLastName( entityClientLastName( entity ) );

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

    private LocalDateTime entityWorkoutStartTime(AppointmentEntity appointmentEntity) {
        if ( appointmentEntity == null ) {
            return null;
        }
        WorkoutEntity workout = appointmentEntity.getWorkout();
        if ( workout == null ) {
            return null;
        }
        LocalDateTime startTime = workout.getStartTime();
        if ( startTime == null ) {
            return null;
        }
        return startTime;
    }

    private LocalDateTime entityWorkoutEndTime(AppointmentEntity appointmentEntity) {
        if ( appointmentEntity == null ) {
            return null;
        }
        WorkoutEntity workout = appointmentEntity.getWorkout();
        if ( workout == null ) {
            return null;
        }
        LocalDateTime endTime = workout.getEndTime();
        if ( endTime == null ) {
            return null;
        }
        return endTime;
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

    private String entityTrainerFirstName(AppointmentEntity appointmentEntity) {
        if ( appointmentEntity == null ) {
            return null;
        }
        TrainerEntity trainer = appointmentEntity.getTrainer();
        if ( trainer == null ) {
            return null;
        }
        String firstName = trainer.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String entityTrainerLastName(AppointmentEntity appointmentEntity) {
        if ( appointmentEntity == null ) {
            return null;
        }
        TrainerEntity trainer = appointmentEntity.getTrainer();
        if ( trainer == null ) {
            return null;
        }
        String lastName = trainer.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
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

    private String entityClientFirstName(AppointmentEntity appointmentEntity) {
        if ( appointmentEntity == null ) {
            return null;
        }
        ClientEntity client = appointmentEntity.getClient();
        if ( client == null ) {
            return null;
        }
        String firstName = client.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String entityClientLastName(AppointmentEntity appointmentEntity) {
        if ( appointmentEntity == null ) {
            return null;
        }
        ClientEntity client = appointmentEntity.getClient();
        if ( client == null ) {
            return null;
        }
        String lastName = client.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }
}
