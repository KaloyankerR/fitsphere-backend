package s3.ind.domain.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import s3.ind.domain.Trainer;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllTrainersResponse {
    private List<GetTrainerResponse> trainers;
}
