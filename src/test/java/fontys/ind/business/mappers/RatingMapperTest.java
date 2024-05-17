package fontys.ind.business.mappers;

import fontys.ind.domain.response.rating.GetRatingResponse;
import fontys.ind.persistence.entity.ClientEntity;
import fontys.ind.persistence.entity.RatingEntity;
import fontys.ind.persistence.entity.TrainerEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RatingMapperTest {
    @Autowired
    private RatingMapper ratingMapper;

    @Autowired
    private TrainerMapper trainerMapper;

    @Autowired
    private ClientMapper clientMapper;

    @Test
    void fromEntityToResponse_shouldMapCorrectly() {
        // Arrange
        TrainerEntity trainer = new TrainerEntity();
        trainer.setUserId(1);
        trainer.setFirstName("Trainer Name");

        ClientEntity client = new ClientEntity();
        client.setUserId(2);
        client.setFirstName("Client Name");

        RatingEntity ratingEntity = new RatingEntity();
        ratingEntity.setId(3L);
        ratingEntity.setRating(5);
        ratingEntity.setComment("Great!");
        ratingEntity.setTrainer(trainer);
        ratingEntity.setClient(client);

        // Act
        GetRatingResponse response = ratingMapper.fromEntityToResponse(ratingEntity);

        // Assert
        assertNotNull(response);
        assertEquals(3, response.getId());
        assertEquals(5, response.getRating());
        assertEquals("Great!", response.getComment());
        assertNotNull(response.getTrainer());
        assertEquals("Trainer Name", response.getTrainer().getFirstName());
        assertNotNull(response.getClient());
        assertEquals("Client Name", response.getClient().getFirstName());
    }
}
