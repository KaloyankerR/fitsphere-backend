package s3.ind.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWorkoutRequest {
    private Integer id;
    private Integer trainerId;
    private String title;
    private String description;
}
