package fontys.ind.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;
import fontys.ind.domain.RoleEnum;

@Entity
@Table(name = "users")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @NotBlank
    @Length(min = 3, max = 20)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Length(min = 3, max = 20)
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Length(min = 5, max = 50)
    @Column(name = "email")
    private String email;

    @NotBlank
    // @Length(max = 100)
    @Column(name = "password")
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEnum role;
}




