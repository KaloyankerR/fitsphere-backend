package fontys.ind.business.mappers;

import fontys.ind.domain.request.user.CreateUserRequest;
import fontys.ind.persistence.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role")
    ClientEntity fromRequestToEntity(CreateUserRequest request);
}
