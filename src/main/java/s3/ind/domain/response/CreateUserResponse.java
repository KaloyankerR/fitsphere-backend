package s3.ind.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserResponse {
    private Integer id;
    // private String firstName;
}