package fontys.ind.business.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import fontys.ind.business.AppointmentService;
import fontys.ind.domain.request.appointment.CreateAppointmentRequest;
import jakarta.transaction.Transactional;
import fontys.ind.domain.response.appointment.CreateAppointmentResponse;
import fontys.ind.persistence.*;
import fontys.ind.persistence.entity.*;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final TrainerRepository trainerRepository;
    private final ClientRepository clientRepository;
    private final WorkoutRepository workoutRepository;
    private final AppointmentRepository appointmentRepository;
    private static final String NOT_FOUND_SUFFIX = " not found.";

    @Override
    @Transactional
    public CreateAppointmentResponse createAppointment(CreateAppointmentRequest request) {
        Optional<TrainerEntity> trainerEntityOptional = trainerRepository.findById(Long.valueOf(request.getTrainerId()));
        TrainerEntity trainerEntity = trainerEntityOptional.orElseThrow(() ->
                new EntityNotFoundException("Trainer with ID " + request.getTrainerId() + NOT_FOUND_SUFFIX));

        Optional<ClientEntity> clientEntityOptional = clientRepository.findById(Long.valueOf(request.getClientId()));
        ClientEntity clientEntity = clientEntityOptional.orElseThrow(() ->
                new EntityNotFoundException("Client with ID " + request.getClientId() + NOT_FOUND_SUFFIX));

        Optional<WorkoutEntity> workoutEntityOptional = workoutRepository.findById(request.getWorkoutId());
        WorkoutEntity workoutEntity = workoutEntityOptional.orElseThrow(() ->
                new EntityNotFoundException("Workout with ID " + request.getWorkoutId() + NOT_FOUND_SUFFIX));

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
