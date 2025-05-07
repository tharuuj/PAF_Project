package com.linhtch90.psnbackend.service;

import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.linhtch90.psnbackend.entity.NotificationEntity;
import com.linhtch90.psnbackend.repository.NotificationRepository;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepo;

    public void createNotification(String recipientUserId, String senderUserId, String senderFullname, String postId, String commentId, String type, String message) {
        NotificationEntity notification = new NotificationEntity();
        notification.setRecipientUserId(recipientUserId);
        notification.setSenderUserId(senderUserId);
        notification.setSenderFullname(senderFullname);
        notification.setPostId(postId);
        notification.setCommentId(commentId);
        notification.setType(type);
        notification.setMessage(message);
        notification.setCreatedAt(Instant.now());
        notificationRepo.save(notification);
    }

    public List<NotificationEntity> getUserNotifications(String recipientUserId) {
        return notificationRepo.findByRecipientUserIdOrderByCreatedAtDesc(recipientUserId);
    }


    public void deleteById(String id) {
        notificationRepo.deleteById(id);
    }

    public void markAsRead(String id) {
        NotificationEntity notification = notificationRepo.findById(id).orElse(null);
        if (notification != null) {
            notification.setRead(true);
            notificationRepo.save(notification);
        }
    }
}
