package fontys.ind.business.impl;

import fontys.ind.business.mappers.AppointmentMapper;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        TrainerEntity trainerEntity = trainerRepository.findById(Long.valueOf(request.getTrainerId()))
                .orElseThrow(() -> new EntityNotFoundException("Trainer with ID " + request.getTrainerId() + NOT_FOUND_SUFFIX));

        ClientEntity clientEntity = clientRepository.findById(Long.valueOf(request.getClientId()))
                .orElseThrow(() -> new EntityNotFoundException("Client with ID " + request.getClientId() + NOT_FOUND_SUFFIX));

        WorkoutEntity workoutEntity = workoutRepository.findById(request.getWorkoutId())
                .orElseThrow(() -> new EntityNotFoundException("Workout with ID " + request.getWorkoutId() + NOT_FOUND_SUFFIX));

        AppointmentEntity newAppointment = AppointmentEntity.builder()
                .workout(workoutEntity)
                .trainer(trainerEntity)
                .client(clientEntity)
                .build();
        // TODO: include in the mapper

        AppointmentEntity savedAppointment = appointmentRepository.save(newAppointment);
        return CreateAppointmentResponse.builder().id(savedAppointment.getId()).build();
    }

    @Override
    @Transactional
    public void deleteAppointment(Integer id) {
        if (!appointmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Appointment with ID " + id + NOT_FOUND_SUFFIX);
        }
        appointmentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public GetAllAppointmentsResponse getAllAppointments() {
        List<AppointmentEntity> appointmentEntityList = appointmentRepository.findAll();

        List<GetAppointmentResponse> appointments = appointmentEntityList.stream()
                .map(appointmentMapper::fromEntityToResponse)
                .collect(Collectors.toList());

        return new GetAllAppointmentsResponse(appointments);
    }

    @Override
    @Transactional
    public GetAllAppointmentsResponse getAllAppointmentsByUser(Integer id) {
        List<AppointmentEntity> trainerAppointments = appointmentRepository.findAllByTrainerUserId(id);
        List<AppointmentEntity> clientAppointments = appointmentRepository.findAllByClientUserId(id);

        List<GetAppointmentResponse> appointments = new ArrayList<>();
        appointments.addAll(trainerAppointments.stream()
                .map(appointmentMapper::fromEntityToResponse)
                .toList());
        appointments.addAll(clientAppointments.stream()
                .map(appointmentMapper::fromEntityToResponse)
                .toList());

        return new GetAllAppointmentsResponse(appointments);
    }
}
