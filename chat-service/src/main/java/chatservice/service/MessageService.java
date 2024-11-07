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
                .senderId(senderId)
                .recipientId(recipientId)

                .sender(sender)
                .recipient(recipient)

                .build();
        message = messageRepository.save(message);

        return getMessageDtoResponse(message);
    }


//
    public  MessageDtoResponse getMessagesByRecipientId(Long recipientId, Long senderId) {
        User sender= getUserFromDbById(senderId);
        User recipient= getUserFromDbById(recipientId);
        // List<Message> messageList = messageRepository.findMessagesBySenderAndRecipient(senderId, recipientId);


        return getListMessageDtoResponse(messageList);
    }










//    public List<QuestionDtoResponse> getQuestionsByProduct(Long sellerId, Long productId) {
//        Advertisement advertisement = getProductFromDbDById(productId);
//        if (advertisement.getOwner().getId() != sellerId) {
//            throw new IllegalArgumentException();
//        }
//        List<Message> messageList = advertisement.getMessages();
//
//        return getListQuestionDtoResponse(messageList);
//    }


    public MessageDtoResponse getMessageById(Long senderId, Long messageId) {
        Message message = getMessageFromDbById(messageId);

        return getMessageDtoResponse(message);
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
                .build();
    }













//    private Advertisement getProductFromDbDById(Long advertisementId) {
//        return advertisementRepository.findById(advertisementId)
//                .orElseThrow(() -> new IllegalArgumentException());
//    }

    private User getUserFromDbById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException("Пользователя с id = " + userId + "нет в базе данных"));
    }

    private Message getMessageFromDbById(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new MessageException("Сообщения с id = " + messageId + " не существует"));
    }


}
