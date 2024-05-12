package fontys.ind.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import fontys.ind.domain.response.user.GetTrainerResponse;
import fontys.ind.persistence.entity.TrainerEntity;

@Mapper(componentModel = "spring", uses = AppointmentMapper.class)
public interface TrainerMapper {
    @Mapping(target = "id", source = "entity.userId")
    @Mapping(target = "firstName", source = "entity.firstName")
    @Mapping(target = "lastName", source = "entity.lastName")
    @Mapping(target = "email", source = "entity.email")
    @Mapping(target = "password", source = "entity.password")
    @Mapping(target = "role", source = "entity.role")
    @Mapping(target = "bio", source = "entity.bio")
    @Mapping(target = "igLink", source = "entity.igLink")
    @Mapping(target = "appointmentList", source = "appointments")
    GetTrainerResponse fromEntityToResponse(TrainerEntity entity);

    @Mapping(target = "userId", source = "response.id")
    @Mapping(target = "firstName", source = "response.firstName")
    @Mapping(target = "lastName", source = "response.lastName")
    @Mapping(target = "email", source = "response.email")
    @Mapping(target = "password", source = "response.password")
    @Mapping(target = "role", source = "response.role")
    @Mapping(target = "bio", source = "response.bio")
    TrainerEntity fromResponseToEntity(GetTrainerResponse response);

}
