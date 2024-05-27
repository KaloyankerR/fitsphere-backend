package fontys.ind.business.mappers;

import fontys.ind.domain.response.appointment.GetAppointmentResponse;
import fontys.ind.persistence.entity.AppointmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "workoutId", source = "entity.workout.id")
    @Mapping(target = "workoutTitle", source = "entity.workout.title")
    @Mapping(target = "startTime", source = "entity.workout.startTime")
    @Mapping(target = "endTime", source = "entity.workout.endTime")
    @Mapping(target = "trainerId", source = "entity.trainer.userId")
    @Mapping(target = "trainerFirstName", source = "entity.trainer.firstName")
    @Mapping(target = "trainerLastName", source = "entity.trainer.lastName")
    @Mapping(target = "clientId", source = "entity.client.userId")
    @Mapping(target = "clientFirstName", source = "entity.client.firstName")
    @Mapping(target = "clientLastName", source = "entity.client.lastName")
    GetAppointmentResponse fromEntityToResponse(AppointmentEntity entity);


}
