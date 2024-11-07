package chatservice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    //private Timestamp sendDate;
    private LocalDateTime sendDate;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long senderId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipientId;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Advertisement advertisement;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;
}
