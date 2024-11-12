package chatservice.service;

import chatservice.domain.*;
import chatservice.dto.*;
import chatservice.exception.MessageException;
import chatservice.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import chatservice.repository.MessageRepository;
import chatservice.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {

    //private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;


    public MessageDtoResponse sendMessage(Long senderId,  Long  recipientId, MessageDtoRequest messageDtoRequest) {
        //Advertisement advertisement = getProductFromDbDById(productId);
        User sender= getUserFromDbById(senderId);
        User recipient= getUserFromDbById(recipientId);

        Message message = Message.builder()
                .text(messageDtoRequest.getText())
                .sendDate(LocalDateTime.now())
                //.advertisement(advertisement)
                .sender(sender)
                .recipient(recipient)
                .build();
        message = messageRepository.save(message);

        return getMessageDtoResponse(message);
    }

    public List<MessageDtoResponse> getMessagesByRecipientId(Long recipientId, Long senderId) {
        User recipient = getUserFromDbById(recipientId);
        User sender = getUserFromDbById(senderId);
        List<Message> messages = messageRepository.findByRecipientAndSender(recipient, sender);
        return getListMessageDtoResponse(messages);
//        // Преобразование Message в MessageDtoResponse
//        return messages.stream()
//                .map(message -> new MessageDtoResponse(
//                        message.getId(),
//                        message.getSender().getId(),
//                        message.getRecipient().getId(),
//                        message.getText(),
//                        message.getTimestamp()))
//                .collect(Collectors.toList());
    }



    private List<MessageDtoResponse> getListMessageDtoResponse(List<Message> messages) {
        List<MessageDtoResponse> result = new ArrayList<>();
        for (Message message : messages) {
            result.add(getMessageDtoResponse(message));
        }
        return result;
    }

    private MessageDtoResponse getMessageDtoResponse(Message message) {
        return MessageDtoResponse.builder()
                .id(message.getId())
                .text(message.getText())
                .sendDate(message.getSendDate())
                .senderId(message.getSender().getId())
                .recipientId(message.getRecipient().getId())
                //.advertisementId(message.getAdvertisement().getId())
                .build();
    }


    private User getUserFromDbById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException("Пользователя с id = " + userId + "нет в базе данных"));
    }

    private Message getMessageFromDbById(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new MessageException("Сообщения с id = " + messageId + " не существует"));
    }


}
