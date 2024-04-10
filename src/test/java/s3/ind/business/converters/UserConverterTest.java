package s3.ind.business.converters;

import org.junit.jupiter.api.Test;
import s3.ind.business.dto.UserDTO;
import s3.ind.domain.User;

import static org.junit.jupiter.api.Assertions.*;

class UserConverterTest {

    @Test
    void fromDTO() {
        UserConverter userConverter = new UserConverter();

        // Create a UserDTO object with sample data
        UserDTO userDTO = UserDTO.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .phoneNumber("123456789")
                .build();

        // Convert UserDTO to User using the converter
        User user = userConverter.fromDTO(userDTO);

        // Assert that the conversion is correct
        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getFirstName(), user.getFirstName());
        assertEquals(userDTO.getLastName(), user.getLastName());
        assertEquals(userDTO.getEmail(), user.getEmail());
        assertEquals(userDTO.getPhoneNumber(), user.getPhoneNumber());
    }

    @Test
    void toDTO() {
        UserConverter userConverter = new UserConverter();

        // Create a User object with sample data
        User user = User.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .phoneNumber("123456789")
                .build();

        // Convert User to UserDTO using the converter
        UserDTO userDTO = userConverter.toDTO(user);

        // Assert that the conversion is correct
        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getFirstName(), userDTO.getFirstName());
        assertEquals(user.getLastName(), userDTO.getLastName());
        assertEquals(user.getEmail(), userDTO.getEmail());
        assertEquals(user.getPhoneNumber(), userDTO.getPhoneNumber());
    }
}