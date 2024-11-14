package chatservice.controller;

import chatservice.dto.*;
import lombok.RequiredArgsConstructor;
import chatservice.service.MessageService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/message")

public class MessageController {
    private final MessageService messageService;

    // отправить сообщ
    @PostMapping("/{sender-id}/{recipient-id}")
    public ResponseEntity<MessageDtoResponse> sendMessage(
        @PathVariable("sender-id") Long senderId,
        @PathVariable("recipient-id") Long recipientId,
        @RequestBody MessageDtoRequest messageDtoRequest) {
    return ResponseEntity.ok(messageService.sendMessage(senderId, recipientId,  messageDtoRequest));
    }


    //получить мои сообщения от какого то отправителя
    @GetMapping("/{recipient-id}/{sender-id}")
    public ResponseEntity<List<MessageDtoResponse>> getMessages (
        @PathVariable("recipient-id") Long recipientId,
        @PathVariable("sender-id") Long senderId )
    {
        List<MessageDtoResponse> messages = messageService.getMessagesByRecipientId(recipientId, senderId);
        return ResponseEntity.ok(messages);
    }

}
