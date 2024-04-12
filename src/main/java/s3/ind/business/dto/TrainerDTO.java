package s3.ind.business.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

// @Data
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerDTO extends UserDTO {
    private String bio;
    private String igLink;
    private String image;
}
