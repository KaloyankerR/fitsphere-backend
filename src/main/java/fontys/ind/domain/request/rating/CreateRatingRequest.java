package fontys.ind.domain.request.rating;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRatingRequest {
    private int rating;
    private String comment;
    private Integer trainerId;
    private Integer clientId;
}
