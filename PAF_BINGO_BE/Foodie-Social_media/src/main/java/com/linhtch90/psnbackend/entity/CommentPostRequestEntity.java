package com.linhtch90.psnbackend.entity;

// Import Lombok annotations
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok generates getters, setters, constructors, and more
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentPostRequestEntity {
    
    // The comment details (content, user, timestamp, etc.)
    private CommentEntity commentEntity;
    
    // The ID of the post that the comment belongs to
    private IdObjectEntity postId;
}
