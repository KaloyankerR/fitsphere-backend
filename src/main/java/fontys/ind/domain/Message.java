package fontys.ind.domain;

import lombok.Data;

@Data
public class Message {
    private Integer id;
    private String receiver;
    private String sender;
    private String messageText;
}
