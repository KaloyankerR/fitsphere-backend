package fontys.ind.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "clients")
@PrimaryKeyJoinColumn(name = "user_id")
public class ClientEntity extends UserEntity {
//    @NotBlank
//    @Column(name = "University")
//    private String university;
}
