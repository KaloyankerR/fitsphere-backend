package s3.ind.business.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import s3.ind.business.exception.EmailAlreadyExistsException;
import s3.ind.business.exception.InvalidWorkoutException;
import s3.ind.business.exception.TitleAlreadyExistsException;
import s3.ind.business.mappers.WorkoutMapper;
import s3.ind.domain.request.workout.CreateWorkoutRequest;
import s3.ind.domain.request.workout.UpdateWorkoutRequest;
import s3.ind.domain.response.workout.CreateWorkoutResponse;
import s3.ind.persistence.TrainerRepository;
import s3.ind.persistence.WorkoutRepository;
import s3.ind.persistence.entity.TrainerEntity;
import s3.ind.persistence.entity.WorkoutEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkoutServiceImplTest {

    @Mock
    private WorkoutRepository workoutRepository;

    @Mock
    private TrainerRepository trainerRepository;


    @InjectMocks
    private WorkoutServiceImpl workoutService;

    @Test
    void createWorkout_WhenTitleExists_ThrowsException() {
        CreateWorkoutRequest request = new CreateWorkoutRequest(1, "Yoga Session", "A relaxing yoga workout");
        when(workoutRepository.existsByTitle(request.getTitle())).thenReturn(true);

        assertThrows(TitleAlreadyExistsException.class, () -> workoutService.createWorkout(request));
    }

    @Test
    void createWorkout_Successful_ReturnsResponse() {
        CreateWorkoutRequest request = new CreateWorkoutRequest(1, "Yoga Session", "A relaxing yoga workout");
        TrainerEntity trainer = new TrainerEntity();
        trainer.setUserId(1);
        WorkoutEntity savedWorkout = new WorkoutEntity();
        savedWorkout.setId(1);
        savedWorkout.setTitle(request.getTitle());
        savedWorkout.setDescription(request.getDescription());
        savedWorkout.setTrainer(trainer);

        when(workoutRepository.existsByTitle(request.getTitle())).thenReturn(false);
        when(trainerRepository.findById(Long.valueOf(request.getTrainerId()))).thenReturn(Optional.of(trainer));
        when(workoutRepository.save(any(WorkoutEntity.class))).thenReturn(savedWorkout);

        CreateWorkoutResponse response = workoutService.createWorkout(request);

        assertNotNull(response);
        assertEquals(1, response.getId());
    }

    @Test
    void deleteWorkout_Successful_DeletesWorkout() {
        Integer id = 1;
        doNothing().when(workoutRepository).deleteById(id);

        workoutService.deleteWorkout(id);

        verify(workoutRepository).deleteById(id);
    }

    @Test
    void updateWorkout_WorkoutNotFound_ThrowsException() {
        UpdateWorkoutRequest request = new UpdateWorkoutRequest(1, "New Title", "New Description");
        when(workoutRepository.findById(request.getId())).thenReturn(Optional.empty());

        assertThrows(InvalidWorkoutException.class, () -> workoutService.updateWorkout(request));
    }

    @Test
    void updateWorkout_Successful_UpdatesFields() {
        UpdateWorkoutRequest request = new UpdateWorkoutRequest(1, "New Title", "New Description");
        WorkoutEntity workout = new WorkoutEntity();
        workout.setId(request.getId());
        workout.setTitle("Old Title");
        workout.setDescription("Old Description");

        when(workoutRepository.findById(request.getId())).thenReturn(Optional.of(workout));
        when(workoutRepository.save(workout)).thenReturn(workout);

        workoutService.updateWorkout(request);

        verify(workoutRepository).save(workout);
        assertEquals("New Title", workout.getTitle());
        assertEquals("New Description", workout.getDescription());
    }
}
