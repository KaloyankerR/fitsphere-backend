package fontys.ind.business;

import fontys.ind.domain.request.appointment.CreateAppointmentRequest;
import fontys.ind.domain.response.appointment.CreateAppointmentResponse;
import fontys.ind.domain.response.appointment.GetAllAppointmentsResponse;


public interface AppointmentService {

    CreateAppointmentResponse createAppointment(CreateAppointmentRequest request);

    void deleteAppointment(Integer id);

    GetAllAppointmentsResponse getAllAppointments();

    GetAllAppointmentsResponse getAllAppointmentsByUser(Integer id);
}
