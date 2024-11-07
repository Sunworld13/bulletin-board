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
    private String text;
    private Long id;
    private LocalDateTime sendDate;
//    private int senderId;
//    private int recipientId;
}
