package s3.ind.domain.request.workout;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWorkoutRequest {
    private Integer id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
}
