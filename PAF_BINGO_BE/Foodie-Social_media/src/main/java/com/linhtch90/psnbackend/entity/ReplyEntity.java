package com.linhtch90.psnbackend.entity;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyEntity {
    private String id;
    private String userId;
    private String userFullname;
    private String content;
    private Instant createdAt;
}
