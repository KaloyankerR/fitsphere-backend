package fontys.ind.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import fontys.ind.domain.response.user.GetUserResponse;
import fontys.ind.persistence.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", source = "entity.userId")
    @Mapping(target = "firstName", source = "entity.firstName")
    @Mapping(target = "lastName", source = "entity.lastName")
    @Mapping(target = "email", source = "entity.email")
    @Mapping(target = "password", source = "entity.password")
    @Mapping(target = "role", source = "entity.role")
    GetUserResponse fromEntityToResponse(UserEntity entity);

//    @Mapping(target = "userId", ignore = true)
//    @Mapping(target = "firstName", source = "response.firstName")
//    @Mapping(target = "lastName", source = "response.lastName")
//    @Mapping(target = "email", source = "response.email")
//    @Mapping(target = "password", source = "response.password")
//    @Mapping(target = "role", source = "response.role")
//    UserEntity fromResponseToEntity(GetUserResponse response);
}
