package chatservice.repository;

import chatservice.domain.Message;
import chatservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE m.recipient = :recipient AND m.sender = :sender")
    List<Message> findByRecipientAndSender(@Param("recipient") User recipient, @Param("sender") User sender);
}
