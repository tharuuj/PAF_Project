package com.linhtch90.psnbackend.entity;

import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notification")
public class NotificationEntity {
    @Id
    private String id;
    private String recipientUserId; // User who receives notification
    private String senderUserId;    // User who triggered notification
    private String senderFullname;
    private String postId;
    private String commentId;       // Optional, if notification is about comment/reply
    private String type;            // "LIKE", "COMMENT", "REPLY"
    private String message;
    private boolean read = false;
    private Instant createdAt;
}
