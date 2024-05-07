package fontys.ind.business;

import fontys.ind.domain.request.appointment.CreateAppointmentRequest;
import fontys.ind.domain.response.appointment.CreateAppointmentResponse;

public interface AppointmentService {

    CreateAppointmentResponse createAppointment(CreateAppointmentRequest request);
}
