package s3.ind.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import s3.ind.domain.request.user.CreateUserRequest;
import s3.ind.persistence.entity.ClientEntity;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role")
    ClientEntity fromRequestToEntity(CreateUserRequest request);
}
