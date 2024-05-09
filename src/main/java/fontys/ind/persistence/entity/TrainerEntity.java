package fontys.ind.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@Table(name = "trainers")
@PrimaryKeyJoinColumn(name = "user_id")
public class TrainerEntity extends UserEntity {
    @Lob
    @NotBlank
    @Column(name = "bio", nullable = false, columnDefinition = "TEXT")
    private String bio;

    @Column(name = "ig_link")
    private String igLink;

    @Column(name = "profile_image_link")
    private String profileImageUrl;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppointmentEntity> appointments;
//    @ElementCollection
//    @CollectionTable(name = "trainer_appointments", joinColumns = @JoinColumn(name = "trainer_id"))
//    private List<AppointmentEntity> appointments;
}
