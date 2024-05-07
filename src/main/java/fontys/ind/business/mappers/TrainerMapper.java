package fontys.ind.business.mappers;

import fontys.ind.domain.response.user.GetTrainerResponse;
import fontys.ind.persistence.entity.TrainerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TrainerMapper {
    // TrainerMapper INSTANCE = Mappers.getMapper(TrainerMapper.class);

    @Mapping(target = "id", source = "entity.userId")
    @Mapping(target = "firstName", source = "entity.firstName")
    @Mapping(target = "lastName", source = "entity.lastName")
    @Mapping(target = "email", source = "entity.email")
    @Mapping(target = "password", source = "entity.password")
    @Mapping(target = "role", source = "entity.role")
    @Mapping(target = "bio", source = "entity.bio")
    @Mapping(target = "igLink", source = "entity.igLink")
    @Mapping(target = "image", source = "entity.profileImageUrl")
    GetTrainerResponse fromEntityToResponse(TrainerEntity entity);

    @Mapping(target = "userId", source = "response.id")
    @Mapping(target = "firstName", source = "response.firstName")
    @Mapping(target = "lastName", source = "response.lastName")
    @Mapping(target = "email", source = "response.email")
    @Mapping(target = "password", source = "response.password")
    @Mapping(target = "role", source = "response.role")
    @Mapping(target = "bio", source = "response.bio")
    @Mapping(target = "igLink", source = "response.igLink")
    @Mapping(target = "profileImageUrl", source = "response.image")
    TrainerEntity fromResponseToEntity(GetTrainerResponse response);

}
