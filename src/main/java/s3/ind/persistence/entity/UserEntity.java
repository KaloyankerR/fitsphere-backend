package s3.ind.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @NotBlank
    @Length(min = 6, max = 20)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Length(min = 6, max = 20)
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Length(min = 8, max = 50)
    @Column(name = "email")
    private String email;

    @NotBlank
    @Length(min = 5, max = 50)
    @Column(name = "password")
    private String password;

    @Length(min = 8, max = 12)
    @Column(name = "phone_number")
    private String phoneNumber;

    @Length(min = 4, max = 20)
    @Column(name = "role")
    private String role;
}
