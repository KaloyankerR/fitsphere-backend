package fontys.ind.business.mappers;

import fontys.ind.domain.response.user.GetClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import fontys.ind.domain.request.user.CreateUserRequest;
import fontys.ind.persistence.entity.ClientEntity;

@Mapper(componentModel = "spring", uses = AppointmentMapper.class)
public interface ClientMapper {
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role")
    ClientEntity fromRequestToEntity(CreateUserRequest request);

    @Mapping(target = "id", source = "userId")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "appointmentList", source = "appointments")
    GetClientResponse fromEntityToResponse(ClientEntity entity);
}
