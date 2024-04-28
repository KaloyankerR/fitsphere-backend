package s3.ind.domain.request.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @NotNull
    @Length(min = 3, max = 20)
    private String firstName;

    @NotNull
    @Length(min = 3, max = 20)
    private String lastName;

    @NotNull
    @Length(min = 6, max = 50)
    private String email;

    @NotNull
    @Length(min = 5, max = 50)
    private String password;

    @NotNull
    @Length(min = 4, max = 20)
    private String role;
}

