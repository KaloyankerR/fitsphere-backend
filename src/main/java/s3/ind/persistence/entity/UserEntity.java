package s3.ind.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import s3.ind.domain.Role;
//import s3.ind.domain.Role;

@Entity
@Table(name="Users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Integer userId;

    @NotBlank
    @Length(min = 6, max = 20)
    @Column(name = "FirstName")
    private String firstName;

    @NotBlank
    @Length(min = 6, max = 20)
    @Column(name = "LastName")
    private String lastName;

    @NotBlank
    @Length(min = 8, max = 50)
    @Column(name = "Email")
    private String email;

/*
    @NotBlank
    @Length(min = 5, max = 50)
    @Column(name = "PasswordHash")
    private String password;
*/

    @Length(min = 8, max = 12)
    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "Role")
    private Role role;
}
