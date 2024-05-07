package fontys.ind.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "admins")
public class AdminEntity extends UserEntity {
}
