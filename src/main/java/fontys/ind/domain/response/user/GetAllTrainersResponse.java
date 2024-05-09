package fontys.ind.domain.response.user;

import fontys.ind.domain.response.ApiWrapperResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GetAllTrainersResponse extends ApiWrapperResponse {
    private List<GetTrainerResponse> trainers;
}
