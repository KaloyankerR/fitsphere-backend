package s3.ind.business.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import s3.ind.domain.RoleEnum;
import s3.ind.domain.response.user.GetUserResponse;
import s3.ind.persistence.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest {

    private UserMapper mapper;

    @BeforeEach
    public void setup() {
        mapper = Mappers.getMapper(UserMapper.class);
    }

    @Test
    public void testFromEntityToResponse() {
        // Setup
        UserEntity entity = new UserEntity();
        entity.setUserId(1);
        entity.setFirstName("Jane");
        entity.setLastName("Doe");
        entity.setEmail("jane.doe@example.com");
        entity.setPassword("securepassword");
        entity.setRole(RoleEnum.ADMIN);

        // Act
        GetUserResponse response = mapper.fromEntityToResponse(entity);

        // Assert
        assertEquals(entity.getUserId(), response.getId());
        assertEquals(entity.getFirstName(), response.getFirstName());
        assertEquals(entity.getLastName(), response.getLastName());
        assertEquals(entity.getEmail(), response.getEmail());
        assertEquals(entity.getPassword(), response.getPassword());
        assertEquals(entity.getRole(), response.getRole());
    }

    @Test
    public void testFromResponseToEntity() {
        // Setup
        GetUserResponse response = new GetUserResponse();
        response.setId(1);
        response.setFirstName("Jane");
        response.setLastName("Doe");
        response.setEmail("jane.doe@example.com");
        response.setPassword("securepassword");
        response.setRole(RoleEnum.ADMIN);

        // Act
        UserEntity entity = mapper.fromResponseToEntity(response);

        // Assert
        assertEquals(response.getId(), entity.getUserId());
        assertEquals(response.getFirstName(), entity.getFirstName());
        assertEquals(response.getLastName(), entity.getLastName());
        assertEquals(response.getEmail(), entity.getEmail());
        assertEquals(response.getPassword(), entity.getPassword());
        assertEquals(response.getRole(), entity.getRole());
    }
}
