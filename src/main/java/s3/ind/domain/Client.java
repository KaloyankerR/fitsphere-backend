package s3.ind.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
//@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends User {
    // private String university;
}