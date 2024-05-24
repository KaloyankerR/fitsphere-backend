package fontys.ind.controller;

import fontys.ind.business.MessageService;
import fontys.ind.domain.Message;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;

    @MessageMapping("/send") //Will react only to the SEND messages to the destination
    public void sendMessage(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        // Extract the username of the sender
        String username = Objects.requireNonNull(headerAccessor.getUser()).getName();

        // Broadcast the message to the intended recipient(s)
        messageService.broadcastMessage(message);

        // Also send the message back to the sender
        messageService.sendMessageToUser(username, message);
    }

}
