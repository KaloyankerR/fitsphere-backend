package fontys.ind.business.mappers;

import fontys.ind.domain.response.appointment.GetAppointmentResponse;
import fontys.ind.persistence.entity.AppointmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @Mapping(target = "workoutId", source = "entity.workout.id")
    @Mapping(target = "trainerId", source = "entity.trainer.userId")
    @Mapping(target = "clientId", source = "entity.client.userId")
    GetAppointmentResponse fromEntityToResponse(AppointmentEntity entity);
}
