package s3.ind.business.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import s3.ind.business.AppointmentService;
import s3.ind.domain.request.appointment.CreateAppointmentRequest;
import jakarta.transaction.Transactional;
import s3.ind.domain.response.appointment.CreateAppointmentResponse;
import s3.ind.persistence.*;
import s3.ind.persistence.entity.*;

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

//    private void validateAppointmentRequest(CreateAppointmentRequest request) {
//        // Add validation logic here
//        // For example:
//        if (request.getStartTime().isAfter(request.getEndTime())) {
//            throw new InvalidAppointmentException("Start time cannot be after end time.");
//        }
//        // You can add more validation rules as needed
//    }

}
