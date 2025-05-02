package com.linhtch90.psnbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

//Add PostByFollowing
public class PostByFollowing {
    private UserEntity user;
    private PostEntity post;
}

