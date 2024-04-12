package s3.ind.business.converters;

import org.junit.jupiter.api.Test;
import s3.ind.business.dto.TrainerDTO;
import s3.ind.domain.Trainer;

import static org.junit.jupiter.api.Assertions.*;

class TrainerConverterTest {

    @Test
    void fromDTO() {
        TrainerDTO dto = TrainerDTO.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .password("password123")
                .phoneNumber("1234567890")
                .bio("Fitness enthusiast")
                .igLink("https://instagram.com/johndoe")
                .image("profile.jpg")
                .build();

        TrainerConverter converter = new TrainerConverter();
        Trainer trainer = converter.fromDTO(dto);

        assertEquals(dto.getId(), trainer.getId());
        assertEquals(dto.getFirstName(), trainer.getFirstName());
        assertEquals(dto.getLastName(), trainer.getLastName());
        assertEquals(dto.getEmail(), trainer.getEmail());
        assertEquals(dto.getPassword(), trainer.getPassword());
        assertEquals(dto.getPhoneNumber(), trainer.getPhoneNumber());
        assertEquals(dto.getBio(), trainer.getBio());
        assertEquals(dto.getIgLink(), trainer.getIgLink());
        assertEquals(dto.getImage(), trainer.getImage());
    }

    @Test
    void toDTO() {
        Trainer trainer = Trainer.builder()
                .id(1)
                .firstName("Jane")
                .lastName("Doe")
                .email("jane.doe@example.com")
                .password("password456")
                .phoneNumber("0987654321")
                .bio("Yoga instructor")
                .igLink("https://instagram.com/janedoe")
                .image("profile.jpg")
                .build();

        TrainerConverter converter = new TrainerConverter();
        TrainerDTO dto = converter.toDTO(trainer);

        assertEquals(trainer.getId(), dto.getId());
        assertEquals(trainer.getFirstName(), dto.getFirstName());
        assertEquals(trainer.getLastName(), dto.getLastName());
        assertEquals(trainer.getEmail(), dto.getEmail());
        assertEquals(trainer.getPassword(), dto.getPassword());
        assertEquals(trainer.getPhoneNumber(), dto.getPhoneNumber());
        assertEquals(trainer.getBio(), dto.getBio());
        assertEquals(trainer.getIgLink(), dto.getIgLink());
        assertEquals(trainer.getImage(), dto.getImage());
    }

    @Test
    public void testFromDTO_NullInput() {
        TrainerConverter converter = new TrainerConverter();
        assertNull(converter.fromDTO(null));
    }

    @Test
    public void testToDTO_NullInput() {
        TrainerConverter converter = new TrainerConverter();
        assertNull(converter.toDTO(null));
    }
}