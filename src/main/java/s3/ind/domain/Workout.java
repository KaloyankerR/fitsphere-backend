package s3.ind.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workout {
    private long id;
    private long trainerId;
    private String title;
    private String description;
    // private
}
