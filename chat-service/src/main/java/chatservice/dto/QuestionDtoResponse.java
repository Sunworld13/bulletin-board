package chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class QuestionDtoResponse {
    private Long id;
    private String text;
    private LocalDateTime sendDate;
    private int senderId;
    private int recipientId;
}
