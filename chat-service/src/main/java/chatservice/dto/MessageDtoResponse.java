package chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MessageDtoResponse {
    private Long id;
    private String text;
    private LocalDateTime sendDate;

    private Long senderId;
    private Long recipientId;
    //private Long advertisementId;
}
