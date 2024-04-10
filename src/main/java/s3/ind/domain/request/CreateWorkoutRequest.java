package s3.ind.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkoutRequest {
    @NotBlank
    private Integer trainerId;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
}