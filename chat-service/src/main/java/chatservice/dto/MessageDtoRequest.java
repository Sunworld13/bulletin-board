package chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MessageDtoRequest {
    private String text;
//    private Long senderId;
//    private Long recipientId;
    //private Long advertisementId;
}
