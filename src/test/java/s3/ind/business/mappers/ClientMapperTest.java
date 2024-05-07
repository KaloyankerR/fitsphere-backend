package s3.ind.business.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import s3.ind.domain.RoleEnum;
import s3.ind.domain.request.user.CreateUserRequest;
import s3.ind.persistence.entity.ClientEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ClientMapperTest {

    private ClientMapper mapper;

    @BeforeEach
    public void setup() {
        mapper = Mappers.getMapper(ClientMapper.class);
    }
    @Test
    void testFromRequestToEntity() {
        // Setup
        CreateUserRequest request = new CreateUserRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("john.doe@example.com");
        request.setPassword("password123");
        request.setRole("CLIENT");

        // Act
        ClientEntity entity = mapper.fromRequestToEntity(request);

        // Assert
        assertEquals(request.getFirstName(), entity.getFirstName(), "First name should match");
        assertEquals(request.getLastName(), entity.getLastName(), "Last name should match");
        assertEquals(request.getEmail(), entity.getEmail(), "Email should match");
        assertEquals(request.getPassword(), entity.getPassword(), "Password should match");
        assertEquals(RoleEnum.CLIENT, entity.getRole(), "Role should match");
    }
}
