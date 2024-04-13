package s3.ind.persistence.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "clients")
public class ClientEntity extends UserEntity {
//    @NotBlank
//    @Column(name = "University")
//    private String university;
}
