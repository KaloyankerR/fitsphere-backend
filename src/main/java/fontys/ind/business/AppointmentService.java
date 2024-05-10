package fontys.ind.business;

import fontys.ind.domain.request.appointment.CreateAppointmentRequest;
import fontys.ind.domain.response.appointment.CreateAppointmentResponse;
import fontys.ind.domain.response.appointment.GetAllAppointmentsResponse;
import fontys.ind.domain.response.appointment.GetAppointmentResponse;

public interface AppointmentService {

    CreateAppointmentResponse createAppointment(CreateAppointmentRequest request);

    GetAllAppointmentsResponse getAllAppointments();
}
