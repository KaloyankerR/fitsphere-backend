package fontys.ind.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @NotNull
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @NotNull
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "workout_id")
    private WorkoutEntity workout;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private TrainerEntity trainer;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;
}
