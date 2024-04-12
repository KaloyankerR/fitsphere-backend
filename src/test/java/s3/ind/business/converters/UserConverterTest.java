package s3.ind.business.converters;

import org.junit.jupiter.api.Test;
import s3.ind.business.dto.UserDTO;
import s3.ind.domain.User;

import static org.junit.jupiter.api.Assertions.*;

class UserConverterTest {

    @Test
    void fromDTO() {
        UserConverter userConverter = new UserConverter();

        UserDTO userDTO = UserDTO.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .phoneNumber("123456789")
                .build();

        User user = userConverter.fromDTO(userDTO);

        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getFirstName(), user.getFirstName());
        assertEquals(userDTO.getLastName(), user.getLastName());
        assertEquals(userDTO.getEmail(), user.getEmail());
        assertEquals(userDTO.getPhoneNumber(), user.getPhoneNumber());
    }

    @Test
    void toDTO() {
        UserConverter userConverter = new UserConverter();

        User user = User.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .phoneNumber("123456789")
                .build();

        UserDTO userDTO = userConverter.toDTO(user);

        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getFirstName(), userDTO.getFirstName());
        assertEquals(user.getLastName(), userDTO.getLastName());
        assertEquals(user.getEmail(), userDTO.getEmail());
        assertEquals(user.getPhoneNumber(), userDTO.getPhoneNumber());
    }
}