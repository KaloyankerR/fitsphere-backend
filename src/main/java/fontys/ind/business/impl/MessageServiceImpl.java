package fontys.ind.business.impl;

import fontys.ind.business.MessageService;
import fontys.ind.domain.Message;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final String destination = "/queue/inboxmessages";
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void broadcastMessage(Message message) {
        messagingTemplate.convertAndSendToUser(message.getReceiver(), destination, message);
        messagingTemplate.convertAndSendToUser(message.getSender(), destination, message);
    }

    @Override
    public void sendMessageToUser(String username, Message message) {
        messagingTemplate.convertAndSendToUser(username, destination, message);
    }
}
