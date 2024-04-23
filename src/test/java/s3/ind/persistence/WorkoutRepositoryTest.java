package s3.ind.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import s3.ind.persistence.entity.RoleEnum;
import s3.ind.persistence.entity.TrainerEntity;
import s3.ind.persistence.entity.WorkoutEntity;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WorkoutRepositoryTest {
    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void existsByTitle() {
        TrainerEntity trainer = TrainerEntity.builder()
                .firstName("John")  // Complies with the minimum length of 4
                .lastName("Donovan")   // Complies with the minimum length of 4
                .email("john.donovan@example.com")  // Complies with the minimum length of 8
                .password("securePassword123")  // Complies with the constraints
                .phoneNumber("1234567890")  // Complies with the length constraints
                .role(RoleEnum.TRAINER)  // Assuming RoleEnum is correctly defined with a value for TRAINERS
                .bio("Experienced fitness trainer specialized in yoga and pilates.")
                .igLink("http://instagram.com/johnfit")
                .profileImageUrl("http://example.com/profile.jpg")
                .build();

        entityManager.persist(trainer);

        WorkoutEntity workout = WorkoutEntity.builder()
                .title("Morning Yoga")
                .description("A refreshing start to your day with yoga.")
                .trainer(trainer)
                .build();

        entityManager.persist(workout);
        entityManager.flush(); // Ensures all pending changes to the database are applied

        // Test check existence
        boolean exists = workoutRepository.existsByTitle("Morning Yoga");
        assertTrue(exists, "Workout should exist by title 'Morning Yoga'");

        // Test check non-existence
        boolean notExists = workoutRepository.existsByTitle("Evening Yoga");
        assertFalse(notExists, "Workout should not exist by title 'Evening Yoga'");
    }
}