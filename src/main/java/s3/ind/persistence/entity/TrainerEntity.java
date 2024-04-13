package s3.ind.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
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
}
