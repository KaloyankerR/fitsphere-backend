package fontys.ind.business;

import fontys.ind.domain.Message;

public interface MessageService {
    void broadcastMessage(Message message);
    void sendMessageToUser(String username, Message message);

}
