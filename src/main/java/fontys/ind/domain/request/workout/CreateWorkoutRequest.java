package fontys.ind.domain.request.workout;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkoutRequest {
    @NotNull
    private Integer trainerId;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
}
