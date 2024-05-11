package fontys.ind.business.impl;

import fontys.ind.business.mappers.AppointmentMapper;
import fontys.ind.domain.request.appointment.UpdateAppointmentRequest;
import fontys.ind.domain.response.appointment.GetAllAppointmentsResponse;
import fontys.ind.domain.response.appointment.GetAppointmentResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import fontys.ind.business.AppointmentService;
import fontys.ind.domain.request.appointment.CreateAppointmentRequest;
import jakarta.transaction.Transactional;
import fontys.ind.domain.response.appointment.CreateAppointmentResponse;
import fontys.ind.persistence.*;
import fontys.ind.persistence.entity.*;

import java.io.InvalidClassException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final TrainerRepository trainerRepository;
    private final ClientRepository clientRepository;
    private final WorkoutRepository workoutRepository;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
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

    @Override
    @Transactional
    public void updateAppointment(UpdateAppointmentRequest request) throws InvalidClassException {
        Optional<AppointmentEntity> appointmentEntityOptional = appointmentRepository.findById(request.getId());

        if (appointmentEntityOptional.isEmpty()) {
            // TODO: change to a more detail exception
            throw new InvalidClassException("Invalid id for appointment.");
        }

        AppointmentEntity appointmentEntity = appointmentEntityOptional.get();
        updateFields(request, appointmentEntity);
    }

    @Override
    @Transactional
    public void deleteAppointment(Integer id) {
        appointmentRepository.deleteById(id);
    }

    private void updateFields(UpdateAppointmentRequest request, AppointmentEntity entity){
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());

        appointmentRepository.save(entity);
    }

    @Override
    public GetAllAppointmentsResponse getAllAppointments() {
        List<AppointmentEntity> appointmentEntityList = appointmentRepository.findAll();

        List<GetAppointmentResponse> appointments = appointmentEntityList.stream()
                .map(appointmentMapper::fromEntityToResponse)
                .toList();

        final GetAllAppointmentsResponse response = new GetAllAppointmentsResponse();
        response.setAppointments(appointments);
        return response;
    }

}
