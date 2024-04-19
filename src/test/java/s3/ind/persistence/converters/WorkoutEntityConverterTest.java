package s3.ind.persistence.converters;

import org.junit.jupiter.api.Test;
import s3.ind.domain.Workout;
import s3.ind.persistence.entity.WorkoutEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WorkoutEntityConverterTest {

    @Test
    void fromEntity() {
        TrainerEntityConverter trainerConverter = mock(TrainerEntityConverter.class);

        WorkoutEntity entity = new WorkoutEntity();
        entity.setId(1);
        entity.setTitle("Test Workout");
        entity.setDescription("This is a test workout");

        when(trainerConverter.fromEntity(any())).thenReturn(null); // Mocked Trainer

        WorkoutEntityConverter converter = new WorkoutEntityConverter(trainerConverter);
        Workout workout = converter.fromEntity(entity);

        assertEquals(entity.getId(), workout.getId());
        assertEquals(entity.getTitle(), workout.getTitle());
        assertEquals(entity.getDescription(), workout.getDescription());
    }

    @Test
    void toEntity() {
        TrainerEntityConverter trainerConverter = mock(TrainerEntityConverter.class);

        Workout workout = Workout.builder()
                .id(1)
                .title("Test Workout")
                .description("This is a test workout")
                .build();

        when(trainerConverter.toEntity(any())).thenReturn(null); // Mocked TrainerEntity

        WorkoutEntityConverter converter = new WorkoutEntityConverter(trainerConverter);
        WorkoutEntity entity = converter.toEntity(workout);

        assertEquals(workout.getId(), entity.getId());
        assertEquals(workout.getTitle(), entity.getTitle());
        assertEquals(workout.getDescription(), entity.getDescription());
    }

    @Test
    public void testFromEntity_NullInput() {
        TrainerEntityConverter trainerConverter = mock(TrainerEntityConverter.class);
        WorkoutEntityConverter converter = new WorkoutEntityConverter(trainerConverter);
        assertNull(converter.fromEntity(null));
    }

    @Test
    public void testToEntity_NullInput() {
        TrainerEntityConverter trainerConverter = mock(TrainerEntityConverter.class);
        WorkoutEntityConverter converter = new WorkoutEntityConverter(trainerConverter);
        assertNull(converter.toEntity(null));
    }
}