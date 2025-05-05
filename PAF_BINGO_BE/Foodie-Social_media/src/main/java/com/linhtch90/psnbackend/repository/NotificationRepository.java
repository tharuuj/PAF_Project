package com.linhtch90.psnbackend.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.linhtch90.psnbackend.entity.NotificationEntity;

@Repository
public interface NotificationRepository extends MongoRepository<NotificationEntity, String> {
    List<NotificationEntity> findByRecipientUserIdOrderByCreatedAtDesc(String recipientUserId);
}
