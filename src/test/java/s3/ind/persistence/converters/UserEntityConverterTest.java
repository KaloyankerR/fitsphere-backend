package s3.ind.persistence.converters;

import org.junit.jupiter.api.Test;
import s3.ind.domain.User;
import s3.ind.persistence.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityConverterTest {

    @Test
    void fromEntity() {
        UserEntity entity = new UserEntity();
        entity.setUserId(1);
        entity.setFirstName("John");
        entity.setLastName("Doe");
        entity.setEmail("john.doe@example.com");
        entity.setPassword("password123");

        UserEntityConverter converter = new UserEntityConverter();
        User user = converter.fromEntity(entity);

        assertEquals(entity.getUserId(), user.getId());
        assertEquals(entity.getFirstName(), user.getFirstName());
        assertEquals(entity.getLastName(), user.getLastName());
        assertEquals(entity.getEmail(), user.getEmail());
        assertEquals(entity.getPassword(), user.getPassword());
    }

    @Test
    void toEntity() {
        User user = User.builder()
                .id(1)
                .firstName("Jane")
                .lastName("Doe")
                .email("jane.doe@example.com")
                .password("password456")
                .build();

        UserEntityConverter converter = new UserEntityConverter();
        UserEntity entity = converter.toEntity(user);

        assertEquals(user.getId(), entity.getUserId());
        assertEquals(user.getFirstName(), entity.getFirstName());
        assertEquals(user.getLastName(), entity.getLastName());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testFromEntity_NullInput() {
        UserEntityConverter converter = new UserEntityConverter();
        assertNull(converter.fromEntity(null));
    }

    @Test
    public void testToEntity_NullInput() {
        UserEntityConverter converter = new UserEntityConverter();
        assertNull(converter.toEntity(null));
    }
}