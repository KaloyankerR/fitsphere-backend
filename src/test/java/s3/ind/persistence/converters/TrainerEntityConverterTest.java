package s3.ind.persistence.converters;

import org.junit.jupiter.api.Test;
import s3.ind.domain.Trainer;
import s3.ind.persistence.entity.TrainerEntity;

import static org.junit.jupiter.api.Assertions.*;

class TrainerEntityConverterTest {

    @Test
    void fromEntity() {
        TrainerEntity entity = new TrainerEntity();
        entity.setUserId(1);
        entity.setFirstName("John");
        entity.setLastName("Doe");
        entity.setEmail("john.doe@example.com");
        entity.setPassword("password123");
        entity.setBio("Fitness enthusiast");
        entity.setIgLink("https://instagram.com/johndoe");
        entity.setProfileImageUrl("profile.jpg");

        TrainerEntityConverter converter = new TrainerEntityConverter();
        Trainer trainer = converter.fromEntity(entity);

        assertEquals(entity.getUserId(), trainer.getId());
        assertEquals(entity.getFirstName(), trainer.getFirstName());
        assertEquals(entity.getLastName(), trainer.getLastName());
        assertEquals(entity.getEmail(), trainer.getEmail());
        assertEquals(entity.getPassword(), trainer.getPassword());
        assertEquals(entity.getBio(), trainer.getBio());
        assertEquals(entity.getIgLink(), trainer.getIgLink());
        assertEquals(entity.getProfileImageUrl(), trainer.getImage());
    }

    @Test
    void toEntity() {
        Trainer trainer = Trainer.builder()
                .id(1)
                .firstName("Jane")
                .lastName("Doe")
                .email("jane.doe@example.com")
                .password("password456")
                .bio("Yoga instructor")
                .igLink("https://instagram.com/janedoe")
                .image("profile.jpg")
                .build();

        TrainerEntityConverter converter = new TrainerEntityConverter();
        TrainerEntity entity = converter.toEntity(trainer);

        assertEquals(trainer.getId(), entity.getUserId());
        assertEquals(trainer.getFirstName(), entity.getFirstName());
        assertEquals(trainer.getLastName(), entity.getLastName());
        assertEquals(trainer.getEmail(), entity.getEmail());
        assertEquals(trainer.getPassword(), entity.getPassword());
        assertEquals(trainer.getBio(), entity.getBio());
        assertEquals(trainer.getIgLink(), entity.getIgLink());
        assertEquals(trainer.getImage(), entity.getProfileImageUrl());
    }

    @Test
    public void testFromEntity_NullInput() {
        TrainerEntityConverter converter = new TrainerEntityConverter();
        assertNull(converter.fromEntity(null));
    }

    @Test
    public void testToEntity_NullInput() {
        TrainerEntityConverter converter = new TrainerEntityConverter();
        assertNull(converter.toEntity(null));
    }
}