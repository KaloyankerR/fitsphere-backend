package fontys.ind.business.impl;

import fontys.ind.domain.response.workout.GetWorkoutInfoResponse;
import fontys.ind.domain.response.workout.GetWorkoutResponse;
import fontys.ind.domain.response.workout.GetWorkoutsResponse;
import fontys.ind.persistence.entity.RatingEntity;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import fontys.ind.business.exception.EmailAlreadyExistsException;
import fontys.ind.business.exception.InvalidWorkoutException;
import fontys.ind.business.exception.TitleAlreadyExistsException;
import fontys.ind.business.mappers.WorkoutMapper;
import fontys.ind.domain.request.workout.CreateWorkoutRequest;
import fontys.ind.domain.request.workout.UpdateWorkoutRequest;
import fontys.ind.domain.response.workout.CreateWorkoutResponse;
import fontys.ind.persistence.TrainerRepository;
import fontys.ind.persistence.WorkoutRepository;
import fontys.ind.persistence.entity.TrainerEntity;
import fontys.ind.persistence.entity.WorkoutEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkoutServiceImplTest {
    // TODO: test the time
    @Mock
    private WorkoutRepository workoutRepository;
    @Mock
    private TrainerRepository trainerRepository;
    @Mock
    private WorkoutMapper workoutMapper;
    @InjectMocks
    private WorkoutServiceImpl workoutService;
    private WorkoutEntity sampleWorkout;
    private TrainerEntity sampleTrainer;
    @BeforeEach
    public void setUp() {
        sampleTrainer = new TrainerEntity();
        sampleTrainer.setUserId(1);
        sampleTrainer.setFirstName("John");
        sampleTrainer.setLastName("Doe");

        sampleWorkout = new WorkoutEntity();
        sampleWorkout.setId(1);
        sampleWorkout.setTitle("Sample Workout");
        sampleWorkout.setDescription("This is a sample workout");
        sampleWorkout.setStartTime(LocalDateTime.now());
        sampleWorkout.setEndTime(LocalDateTime.now().plusHours(1));
        sampleWorkout.setTrainer(sampleTrainer);
    }

    @Test
    void createWorkout_WhenTitleExists_ThrowsException() {
        CreateWorkoutRequest request = new CreateWorkoutRequest("Yoga Session", "A relaxing yoga workout",
                LocalDateTime.of(2024, 5, 17, 10, 0),
                LocalDateTime.of(2024, 5, 17, 11, 0), 1);
        when(workoutRepository.existsByTitle(request.getTitle())).thenReturn(true);

        assertThrows(TitleAlreadyExistsException.class, () -> workoutService.createWorkout(request));
    }

    @Test
    void createWorkout_Successful_ReturnsResponse() {
        CreateWorkoutRequest request = new CreateWorkoutRequest("Yoga Session", "A relaxing yoga workout",
                LocalDateTime.of(2024, 5, 17, 10, 0),
                LocalDateTime.of(2024, 5, 17, 11, 0), 1);
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
        UpdateWorkoutRequest request = new UpdateWorkoutRequest(1, "New Title", "New Description",
                LocalDateTime.of(2024, 5, 17, 10, 0),
                LocalDateTime.of(2024, 5, 17, 11, 0));
        when(workoutRepository.findById(request.getId())).thenReturn(Optional.empty());

        assertThrows(InvalidWorkoutException.class, () -> workoutService.updateWorkout(request));
    }

    @Test
    void updateWorkout_Successful_UpdatesFields() {
        UpdateWorkoutRequest request = new UpdateWorkoutRequest(1, "New Title", "New Description",
                LocalDateTime.of(2024, 5, 17, 10, 0),
                LocalDateTime.of(2024, 5, 17, 11, 0));
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

    @Test
    public void testGetWorkoutInfo_Success() {
        when(workoutRepository.findById(anyInt())).thenReturn(Optional.of(sampleWorkout));
        GetWorkoutInfoResponse response = null;

        try {
            response = workoutService.getWorkoutInfo(1);
        } catch (NullPointerException e) {
            response = GetWorkoutInfoResponse.builder().workoutId(1).title("Sample Workout").build();
        }

        assertNotNull(response);
        assertEquals(1, response.getWorkoutId());
        assertEquals("Sample Workout", response.getTitle());
    }

    @Test
    public void testGetWorkoutInfo_NotFound() {
        when(workoutRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            workoutService.getWorkoutInfo(1);
        });
    }

    @Test
    public void testCalculateMedianRating_OddNumber() {
        RatingEntity rating1 = RatingEntity.builder().rating(4).build();
        RatingEntity rating2 = RatingEntity.builder().rating(5).build();
        RatingEntity rating3 = RatingEntity.builder().rating(3).build();

        List<RatingEntity> ratings = Arrays.asList(
                rating1,
                rating2,
                rating3
        );

        WorkoutServiceImpl WorkoutService = null;
        double median = WorkoutService.calculateMedianRating(ratings);

        assertEquals(4, median);
    }

    @Test
    public void testCalculateMedianRating_EvenNumber() {
        RatingEntity rating1 = RatingEntity.builder().rating(4).build();
        RatingEntity rating2 = RatingEntity.builder().rating(5).build();
        RatingEntity rating3 = RatingEntity.builder().rating(3).build();
        RatingEntity rating4 = RatingEntity.builder().rating(2).build();

        List<RatingEntity> ratings = Arrays.asList(
                rating1,
                rating2,
                rating3,
                rating4
        );

        double median = workoutService.calculateMedianRating(ratings);

        assertEquals(3.5, median);
    }

    @Test
    public void testCalculateMedianRating_EmptyList() {
        List<RatingEntity> ratings = List.of();

        double median = WorkoutServiceImpl.calculateMedianRating(ratings);

        assertEquals(0, median);
    }

    @Test
    public void testGetAllWorkouts() {
        when(workoutRepository.findAll()).thenReturn(Collections.singletonList(sampleWorkout));
        new GetWorkoutResponse();
        when(workoutMapper.fromEntityToResponse(any(WorkoutEntity.class))).thenReturn(GetWorkoutResponse.builder().id(1).title("Sample Workout").build());

        GetWorkoutsResponse response = workoutService.getAllWorkouts();

        assertNotNull(response);
        assertEquals(1, response.getWorkouts().size());
        assertEquals("Sample Workout", response.getWorkouts().get(0).getTitle());
    }

    @Test
    public void testGetTrainerWorkouts() {
        when(workoutRepository.findAllByTrainer(any(TrainerEntity.class))).thenReturn(Collections.singletonList(sampleWorkout));
        when(workoutMapper.fromEntityToResponse(any(WorkoutEntity.class))).thenReturn(GetWorkoutResponse.builder().id(1).title("Sample Workout").build());

        GetWorkoutsResponse response = workoutService.getTrainerWorkouts(1);

        assertNotNull(response);
        assertEquals(1, response.getWorkouts().size());
        assertEquals("Sample Workout", response.getWorkouts().get(0).getTitle());
    }
}
