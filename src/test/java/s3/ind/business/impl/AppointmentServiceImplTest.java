package s3.ind.business.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import s3.ind.domain.request.appointment.CreateAppointmentRequest;
import s3.ind.domain.response.appointment.CreateAppointmentResponse;
import s3.ind.persistence.*;
import s3.ind.persistence.entity.*;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceImplTest {

    @Mock
    private TrainerRepository trainerRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private WorkoutRepository workoutRepository;
    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    private CreateAppointmentRequest request;

    @BeforeEach
    void setUp() {
        // Setup request with mock IDs and times
        request = new CreateAppointmentRequest();
        request.setTrainerId(1);
        request.setClientId(2);
        request.setWorkoutId(3);
        request.setStartTime(LocalDateTime.now().plusHours(2));
        request.setEndTime(LocalDateTime.now().plusHours(4));

        // Mock entities
        TrainerEntity trainer = new TrainerEntity();
        ClientEntity client = new ClientEntity();
        WorkoutEntity workout = new WorkoutEntity();

        when(trainerRepository.findById(Long.valueOf(request.getTrainerId()))).thenReturn(Optional.of(trainer));
        when(clientRepository.findById(Long.valueOf(request.getClientId()))).thenReturn(Optional.of(client));
        when(workoutRepository.findById(request.getWorkoutId())).thenReturn(Optional.of(workout));
    }

    @Test
    void createAppointment_Successful() {
        // Arrange
        when(appointmentRepository.save(any(AppointmentEntity.class))).thenReturn(new AppointmentEntity());

        // Act
        CreateAppointmentResponse response = appointmentService.createAppointment(request);

        // Assert
        assertNotNull(response);
        verify(appointmentRepository).save(any(AppointmentEntity.class));
    }

    @Test
    void createAppointment_WorkoutNotFound_ThrowsEntityNotFoundException() {
        // Arrange
        when(workoutRepository.findById(request.getWorkoutId())).thenReturn(Optional.empty());

        // Assert
        assertThrows(EntityNotFoundException.class, () -> appointmentService.createAppointment(request));
    }
}
