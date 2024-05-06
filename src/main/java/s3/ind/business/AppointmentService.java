package s3.ind.business;

import s3.ind.domain.request.appointment.CreateAppointmentRequest;
import s3.ind.domain.response.appointment.CreateAppointmentResponse;

public interface AppointmentService {

    CreateAppointmentResponse createAppointment(CreateAppointmentRequest request);
}
