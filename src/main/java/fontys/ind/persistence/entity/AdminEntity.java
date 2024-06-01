package fontys.ind.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "user_id")
public class AdminEntity extends UserEntity {
}
