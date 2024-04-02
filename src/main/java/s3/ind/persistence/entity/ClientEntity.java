package s3.ind.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Clients")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    @Id
    @Column(name = "UserID")
    private Integer userId;

    @NotBlank
    @Column(name = "University")
    private String university;

    @OneToOne
    @MapsId
    @JoinColumn(name = "UserID")
    private UserEntity user;
}
