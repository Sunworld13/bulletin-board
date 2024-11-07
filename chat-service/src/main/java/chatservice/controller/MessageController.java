package chatservice.controller;

import chatservice.dto.*;
import lombok.RequiredArgsConstructor;
import chatservice.service.MessageService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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


    //получить
    @GetMapping("/{recipient-id}/{sender-id}")
    public ResponseEntity<MessageDtoResponse> getMessages (
        @PathVariable("recipient-id") Long recipientId,
        @PathVariable("sender-id") Long senderId)
        {
            return ResponseEntity.ok(messageService.getMessagesByRecipientId(recipientId, senderId));
        }

}
