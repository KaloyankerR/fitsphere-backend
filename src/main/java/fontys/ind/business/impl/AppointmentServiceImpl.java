package fontys.ind.business.impl;

import fontys.ind.business.AppointmentService;
import fontys.ind.domain.request.appointment.CreateAppointmentRequest;
import fontys.ind.domain.response.appointment.CreateAppointmentResponse;
import fontys.ind.persistence.AppointmentRepository;
import fontys.ind.persistence.ClientRepository;
import fontys.ind.persistence.TrainerRepository;
import fontys.ind.persistence.WorkoutRepository;
import fontys.ind.persistence.entity.AppointmentEntity;
import fontys.ind.persistence.entity.ClientEntity;
import fontys.ind.persistence.entity.TrainerEntity;
import fontys.ind.persistence.entity.WorkoutEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final TrainerRepository trainerRepository;
    private final ClientRepository clientRepository;
    private final WorkoutRepository workoutRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    @Transactional
    public CreateAppointmentResponse createAppointment(CreateAppointmentRequest request) {
        // Validate inputs
        // validateAppointmentRequest(request);

        Optional<TrainerEntity> trainerEntityOptional = trainerRepository.findById(Long.valueOf(request.getTrainerId()));
        TrainerEntity trainerEntity = trainerEntityOptional.orElseThrow(() ->
                new EntityNotFoundException("Trainer with ID " + request.getTrainerId() + " not found."));

        Optional<ClientEntity> clientEntityOptional = clientRepository.findById(Long.valueOf(request.getClientId()));
        ClientEntity clientEntity = clientEntityOptional.orElseThrow(() ->
                new EntityNotFoundException("Client with ID " + request.getClientId() + " not found."));

        Optional<WorkoutEntity> workoutEntityOptional = workoutRepository.findById(request.getWorkoutId());
        WorkoutEntity workoutEntity = workoutEntityOptional.orElseThrow(() ->
                new EntityNotFoundException("Workout with ID " + request.getWorkoutId() + " not found."));

        AppointmentEntity newAppointment = AppointmentEntity.builder()
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .workout(workoutEntity)
                .trainer(trainerEntity)
                .client(clientEntity)
                .build();

        AppointmentEntity savedAppointment = appointmentRepository.save(newAppointment);

        return CreateAppointmentResponse.builder()
                .id(savedAppointment.getId())
                .build();
    }
}
