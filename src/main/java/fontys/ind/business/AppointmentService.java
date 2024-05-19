package fontys.ind.business;

import fontys.ind.domain.request.appointment.CreateAppointmentRequest;
import fontys.ind.domain.request.appointment.UpdateAppointmentRequest;
import fontys.ind.domain.response.appointment.CreateAppointmentResponse;
import fontys.ind.domain.response.appointment.GetAllAppointmentsResponse;

import java.io.InvalidClassException;

public interface AppointmentService {

    CreateAppointmentResponse createAppointment(CreateAppointmentRequest request);
    void updateAppointment(UpdateAppointmentRequest request) throws InvalidClassException;
    void deleteAppointment(Integer id);
    GetAllAppointmentsResponse getAllAppointments();
    GetAllAppointmentsResponse getAllAppointmentsByUser(Integer id);
}
