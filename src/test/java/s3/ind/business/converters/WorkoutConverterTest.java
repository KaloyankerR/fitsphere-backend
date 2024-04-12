package s3.ind.business.converters;

import org.junit.jupiter.api.Test;
import s3.ind.business.dto.TrainerDTO;
import s3.ind.business.dto.WorkoutDTO;
import s3.ind.domain.Trainer;
import s3.ind.domain.Workout;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WorkoutConverterTest {

    @Test
    void fromDTO() {
        TrainerConverter trainerConverter = mock(TrainerConverter.class);

        WorkoutDTO dto = WorkoutDTO.builder()
                .id(1)
                .trainer(TrainerDTO.builder().build()) // Mocked TrainerDTO
                .title("Test Workout")
                .description("This is a test workout")
                .build();

        when(trainerConverter.fromDTO(dto.getTrainer())).thenReturn(Trainer.builder().build()); // Mocked Trainer

        WorkoutConverter converter = new WorkoutConverter(trainerConverter);
        Workout workout = converter.fromDTO(dto);

        assertEquals(dto.getId(), workout.getId());
    }

    @Test
    void toDTO() {
        TrainerConverter trainerConverter = mock(TrainerConverter.class);

        Workout workout = Workout.builder()
                .id(1)
                .trainer(Trainer.builder().build()) // Mocked Trainer
                .title("Test Workout")
                .description("This is a test workout")
                .build();

        when(trainerConverter.toDTO(workout.getTrainer())).thenReturn(TrainerDTO.builder().build()); // Mocked TrainerDTO

        WorkoutConverter converter = new WorkoutConverter(trainerConverter);
        WorkoutDTO dto = converter.toDTO(workout);

        assertEquals(workout.getId(), dto.getId());
    }

    @Test
    public void testFromDTO_NullInput() {
        TrainerConverter trainerConverter = mock(TrainerConverter.class);
        WorkoutConverter converter = new WorkoutConverter(trainerConverter);
        assertNull(converter.fromDTO(null));
    }

    @Test
    public void testToDTO_NullInput() {
        TrainerConverter trainerConverter = mock(TrainerConverter.class);
        WorkoutConverter converter = new WorkoutConverter(trainerConverter);
        assertNull(converter.toDTO(null));
    }
}