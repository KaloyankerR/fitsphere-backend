package s3.ind.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "workouts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_id")
    private Integer workoutId;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private TrainerEntity trainer;

    @NotBlank
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;
}