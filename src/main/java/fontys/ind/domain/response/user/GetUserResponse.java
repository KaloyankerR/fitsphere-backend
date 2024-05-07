package fontys.ind.domain.response.user;

import fontys.ind.domain.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private RoleEnum role;
}
