package fontys.ind.business.impl;

import fontys.ind.business.mappers.AppointmentMapper;
import fontys.ind.domain.request.appointment.CreateAppointmentRequest;
import fontys.ind.domain.response.appointment.CreateAppointmentResponse;
import fontys.ind.domain.response.appointment.GetAllAppointmentsResponse;
import fontys.ind.domain.response.appointment.GetAppointmentResponse;
import fontys.ind.persistence.*;
import fontys.ind.persistence.entity.*;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AppointmentServiceImplTest {

    @Mock
    private TrainerRepository trainerRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private WorkoutRepository workoutRepository;
    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private AppointmentMapper appointmentMapper;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAppointment_successful() {
        CreateAppointmentRequest request = new CreateAppointmentRequest(
                1, 2, 3
        );
        TrainerEntity trainerEntity = new TrainerEntity();
        ClientEntity clientEntity = new ClientEntity();
        WorkoutEntity workoutEntity = new WorkoutEntity();
        AppointmentEntity appointmentEntity = new AppointmentEntity();

        when(trainerRepository.findById(Long.valueOf(request.getTrainerId()))).thenReturn(Optional.of(trainerEntity));
        when(clientRepository.findById(Long.valueOf(request.getClientId()))).thenReturn(Optional.of(clientEntity));
        when(workoutRepository.findById(request.getWorkoutId())).thenReturn(Optional.of(workoutEntity));
        when(appointmentRepository.save(any(AppointmentEntity.class))).thenReturn(appointmentEntity);

        CreateAppointmentResponse response = appointmentService.createAppointment(request);

        assertNotNull(response);
        verify(appointmentRepository).save(any(AppointmentEntity.class));
    }

    @Test
    void createAppointment_trainerNotFound_throwsException() {
        CreateAppointmentRequest request = new CreateAppointmentRequest(
                1, 2, 3
        );

        when(trainerRepository.findById(Long.valueOf(request.getTrainerId()))).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> appointmentService.createAppointment(request));
    }

    @Test
    void createAppointment_clientNotFound_throwsException() {
        CreateAppointmentRequest request = new CreateAppointmentRequest(
                1, 2, 3
        );
        TrainerEntity trainerEntity = new TrainerEntity();

        when(trainerRepository.findById(Long.valueOf(request.getTrainerId()))).thenReturn(Optional.of(trainerEntity));
        when(clientRepository.findById(Long.valueOf(request.getClientId()))).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> appointmentService.createAppointment(request));
    }

    @Test
    void createAppointment_workoutNotFound_throwsException() {
        CreateAppointmentRequest request = new CreateAppointmentRequest(
                1, 2, 3
        );
        TrainerEntity trainerEntity = new TrainerEntity();
        ClientEntity clientEntity = new ClientEntity();

        when(trainerRepository.findById(Long.valueOf(request.getTrainerId()))).thenReturn(Optional.of(trainerEntity));
        when(clientRepository.findById(Long.valueOf(request.getClientId()))).thenReturn(Optional.of(clientEntity));
        when(workoutRepository.findById(request.getWorkoutId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> appointmentService.createAppointment(request));
    }

    @Test
    void deleteAppointment_successful() {
        int id = 1;

        when(appointmentRepository.existsById(id)).thenReturn(true);

        appointmentService.deleteAppointment(id);

        verify(appointmentRepository).deleteById(id);
    }

    @Test
    void deleteAppointment_appointmentNotFound() {
        int id = 1;

        when(appointmentRepository.existsById(id)).thenReturn(false);

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            appointmentService.deleteAppointment(id);
        });

        assertEquals("Appointment with ID " + id + " not found.", thrown.getMessage());
        verify(appointmentRepository, never()).deleteById(id);
    }

    @Test
    void getAllAppointments_returnsAppointments() {
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        GetAppointmentResponse getAppointmentResponse = new GetAppointmentResponse();
        when(appointmentRepository.findAll()).thenReturn(List.of(appointmentEntity));
        when(appointmentMapper.fromEntityToResponse(appointmentEntity)).thenReturn(getAppointmentResponse);

        GetAllAppointmentsResponse response = appointmentService.getAllAppointments();

        assertNotNull(response);
        assertEquals(1, response.getAppointments().size());
        assertEquals(getAppointmentResponse, response.getAppointments().get(0));
    }

    @Test
    void getAllAppointments_noAppointments_returnsEmpty() {
        when(appointmentRepository.findAll()).thenReturn(List.of());

        GetAllAppointmentsResponse response = appointmentService.getAllAppointments();

        assertNotNull(response);
        assertTrue(response.getAppointments().isEmpty());
    }
}
