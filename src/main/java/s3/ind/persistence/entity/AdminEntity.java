package s3.ind.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Admins")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminEntity {
    @Id
    @Column(name = "UserID")
    private Integer userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "UserID")
    private UserEntity user;
}
