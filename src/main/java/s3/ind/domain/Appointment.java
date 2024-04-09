package s3.ind.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {
    private Integer id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Trainer trainer;
    private Client client;
}
