package com.linhtch90.psnbackend.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comment")
public class CommentEntity {
    @Id
    private String id;

    private String userId;

    private String userFullname;

    private String content;

    private Instant createdAt;

    private List<ReplyEntity> replies = new ArrayList<>();
}
