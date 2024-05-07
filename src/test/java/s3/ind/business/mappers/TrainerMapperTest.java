package s3.ind.business.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import s3.ind.domain.RoleEnum;
import s3.ind.domain.response.user.GetTrainerResponse;
import s3.ind.persistence.entity.TrainerEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainerMapperTest {

    private TrainerMapper mapper;

    @BeforeEach
    public void setup() {
        mapper = Mappers.getMapper(TrainerMapper.class);
    }

    @Test
    public void testFromEntityToResponse() {
        // Setup
        TrainerEntity entity = new TrainerEntity();
        entity.setUserId(1);
        entity.setFirstName("John");
        entity.setLastName("Doe");
        entity.setEmail("john@example.com");
        entity.setPassword("password");
        entity.setRole(RoleEnum.TRAINER);
        entity.setBio("Experienced fitness trainer");
        entity.setIgLink("https://instagram.com/johndoe");
        entity.setProfileImageUrl("http://example.com/image.jpg");

        // Act
        GetTrainerResponse response = mapper.fromEntityToResponse(entity);

        // Assert
        assertEquals(entity.getUserId(), response.getId());
        assertEquals(entity.getFirstName(), response.getFirstName());
        assertEquals(entity.getLastName(), response.getLastName());
        assertEquals(entity.getEmail(), response.getEmail());
        assertEquals(entity.getPassword(), response.getPassword());
        assertEquals(entity.getRole(), response.getRole());
        assertEquals(entity.getBio(), response.getBio());
        assertEquals(entity.getIgLink(), response.getIgLink());
        assertEquals(entity.getProfileImageUrl(), response.getImage());
    }

    @Test
    public void testFromResponseToEntity() {
        // Setup
        GetTrainerResponse response = new GetTrainerResponse();
        response.setId(1);
        response.setFirstName("John");
        response.setLastName("Doe");
        response.setEmail("john@example.com");
        response.setPassword("password");
        response.setRole(RoleEnum.TRAINER);
        response.setBio("Experienced fitness trainer");
        response.setIgLink("https://instagram.com/johndoe");
        response.setImage("http://example.com/image.jpg");

        // Act
        TrainerEntity entity = mapper.fromResponseToEntity(response);

        // Assert
        assertEquals(response.getId(), entity.getUserId());
        assertEquals(response.getFirstName(), entity.getFirstName());
        assertEquals(response.getLastName(), entity.getLastName());
        assertEquals(response.getEmail(), entity.getEmail());
        assertEquals(response.getPassword(), entity.getPassword());
        assertEquals(response.getRole(), entity.getRole());
        assertEquals(response.getBio(), entity.getBio());
        assertEquals(response.getIgLink(), entity.getIgLink());
        assertEquals(response.getImage(), entity.getProfileImageUrl());
    }
}
