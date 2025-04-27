package com.linhtch90.psnbackend.entity;

// Import required classes
import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok annotations:
// @Data generates getters, setters, toString(), equals(), and hashCode()
// @NoArgsConstructor generates a no-argument constructor
// @AllArgsConstructor generates a constructor with all fields
@Data
@NoArgsConstructor
@AllArgsConstructor

// Mark this class as a MongoDB document (collection name is "comment")
@Document(collection = "comment")
public class CommentEntity {

    // MongoDB automatically generates and uses this ID as the primary key
    @Id
    private String id;

    // ID of the user who made the comment
    private String userId;

    // Full name of the user who made the comment
    private String userFullname;

    // Content/text of the comment
    private String content;

    // Timestamp when the comment was created
    private Instant createdAt;
}
