package fontys.ind.business.mappers;

import fontys.ind.domain.request.user.CreateTrainerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import fontys.ind.domain.response.user.GetTrainerResponse;
import fontys.ind.persistence.entity.TrainerEntity;

@Mapper(componentModel = "spring", uses = {AppointmentMapper.class, RatingMapper.class})
public interface TrainerMapper {
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "bio", source = "bio")
    @Mapping(target = "igLink", source = "igLink")
    TrainerEntity fromRequestToEntity(CreateTrainerRequest request);

    @Mapping(target = "id", source = "entity.userId")
    @Mapping(target = "firstName", source = "entity.firstName")
    @Mapping(target = "lastName", source = "entity.lastName")
    @Mapping(target = "email", source = "entity.email")
    @Mapping(target = "password", source = "entity.password")
    @Mapping(target = "role", source = "entity.role")
    @Mapping(target = "bio", source = "entity.bio")
    @Mapping(target = "igLink", source = "entity.igLink")
    @Mapping(target = "appointmentList", source = "appointments")
    @Mapping(target = "ratings", source = "ratings")
    GetTrainerResponse fromEntityToResponse(TrainerEntity entity);
}
