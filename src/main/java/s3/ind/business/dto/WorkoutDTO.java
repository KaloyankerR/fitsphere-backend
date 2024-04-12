package s3.ind.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutDTO {
    private Integer id;
    private TrainerDTO trainer;
    private String title;
    private String description;
}
