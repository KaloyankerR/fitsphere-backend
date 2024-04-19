package s3.ind.domain.request;

// import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private Integer id;
    @NotBlank(message = "First name is required")
    // @Size(min = 6, max = 20, message = "First name must be between 6 and 20 characters")
    private String firstName;
    @NotBlank(message = "Last name is required")
    // @Size(min = 6, max = 20, message = "Last name must be between 6 and 20 characters")
    private String lastName;
    @Email
    @NotBlank(message = "Email is required")
    // @Size(min = 8, max = 50, message = "Email must be between 8 and 50 characters")
    private String email;
    @NotBlank(message = "Password is required")
    // @Size(min = 5, max = 50, message = "Password must be between 5 and 50 characters")
    private String password;
    // @Size(min = 8, max = 12, message = "Phone number must be between 6 and 20 characters")
    private String phoneNumber;
    private String role;
}
