package s3.ind.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="appointments")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @NotBlank
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "workout_id")
    private WorkoutEntity workout;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private TrainerEntity trainer;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;
}
