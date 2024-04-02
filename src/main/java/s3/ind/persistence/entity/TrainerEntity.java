package s3.ind.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Trainers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerEntity {
    @Id
    @Column(name = "UserID")
    private Integer userId;

    @Lob
    @NotBlank
    @Column(name = "Bio", nullable = false, columnDefinition = "TEXT")
    private String bio;

    @Column(name = "InstagramLink")
    private String instagramLink;

    @Column(name = "ProfileImageURL")
    private String profileImageUrl;

    @OneToOne
    @MapsId
    @JoinColumn(name = "UserID")
    private UserEntity user;
}
