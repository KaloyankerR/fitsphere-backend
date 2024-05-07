package s3.ind.business.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mapstruct.factory.Mappers;
import s3.ind.domain.response.user.GetTrainerResponse;
import s3.ind.domain.response.workout.GetWorkoutResponse;
import s3.ind.persistence.entity.TrainerEntity;
import s3.ind.persistence.entity.WorkoutEntity;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class WorkoutMapperTest {
    @InjectMocks
    private WorkoutMapper workoutMapper = Mappers.getMapper(WorkoutMapper.class);

    @BeforeEach
    public void setup() {
//        // Setup mock behavior
//        TrainerEntity trainer = new TrainerEntity();
//        trainer.setUserId(101);
//        trainer.setFirstName("John");
//        trainer.setLastName("Doe");
//
//        GetTrainerResponse trainerResponse = new GetTrainerResponse();
//        trainerResponse.setId(trainer.getUserId());
//        trainerResponse.setFirstName(trainer.getFirstName());
//        trainerResponse.setLastName(trainer.getLastName());
//
//        when(workoutMapper.fromEntityToResponse(trainer)).thenReturn(trainerResponse);
    }

    @Test
    public void testFromEntityToResponse() {
//        // Setup
//        WorkoutEntity entity = new WorkoutEntity();
//        entity.setId(1);
//        entity.setTitle("Morning Routine");
//        entity.setDescription("A comprehensive morning workout.");
//
//        TrainerEntity trainer = new TrainerEntity();
//        trainer.setUserId(101);
//        trainer.setFirstName("John");
//        trainer.setLastName("Doe");
//        entity.setTrainer(trainer);
//
//        // Act
//        GetWorkoutResponse response = workoutMapper.fromEntityToResponse(entity);
//
//        // Assert
//        assertEquals(entity.getId(), response.getId());
//        assertEquals(entity.getTitle(), response.getTitle());
//        assertEquals(entity.getDescription(), response.getDescription());
//        assertEquals(entity.getTrainer().getUserId(), response.getTrainer().getId());
    }
}
