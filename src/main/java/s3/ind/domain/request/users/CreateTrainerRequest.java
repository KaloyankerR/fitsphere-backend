package s3.ind.domain.request.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import s3.ind.persistence.entity.RoleEnum;

@Getter
@Setter
public class CreateTrainerRequest extends CreateUserRequest {
    @NotBlank(message = "Bio cannot be blank")
    @Size(min = 10, message = "Bio must be at least 10 characters long")  // Assuming bio should be somewhat descriptive
    private String bio;

    private String igLink;

    private String profileImageUrl;
}
