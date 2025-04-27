package com.linhtch90.psnbackend.controller;

// Import required classes
import com.linhtch90.psnbackend.entity.CommentEntity;
import com.linhtch90.psnbackend.entity.CommentPostRequestEntity;
import com.linhtch90.psnbackend.entity.IdObjectEntity;
import com.linhtch90.psnbackend.entity.PostEntity;
import com.linhtch90.psnbackend.service.CommentService;
import com.linhtch90.psnbackend.service.ResponseObjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Define this class as a REST controller and set base URL as /api/v1
@RestController
@RequestMapping("/api/v1")
public class CommentController {

    // Inject CommentService to use its methods
    @Autowired
    private CommentService commentService;

    // API to insert a new comment for a post
    @PostMapping("/insertcomment")
    public ResponseEntity<ResponseObjectService> insertComment(@RequestBody CommentPostRequestEntity postedComment) {
        CommentEntity inputComment = postedComment.getCommentEntity();
        IdObjectEntity inputPostId = postedComment.getPostId();
        return new ResponseEntity<ResponseObjectService>(commentService.insertComment(inputComment, inputPostId.getId()), HttpStatus.OK);
    }

    // API to get all comments for a specific post
    @PostMapping("/getcomments") 
    public ResponseEntity<ResponseObjectService> getComments(@RequestBody IdObjectEntity inputPostId) {
        return new ResponseEntity<ResponseObjectService>(commentService.getComments(inputPostId.getId()), HttpStatus.OK);
    }

    // API to get all comments across all posts
    @PostMapping("/getallcomments")
    public ResponseEntity<ResponseObjectService> getAllComments() {
        return new ResponseEntity<ResponseObjectService>(commentService.getAllComments(), HttpStatus.OK);
    }

    // API to edit an existing comment for a post
    @PutMapping("/editcomment")
    public ResponseEntity<ResponseObjectService> putComment(@RequestBody CommentPostRequestEntity postedComment) {
        System.out.println(postedComment); // Log the incoming request
        CommentEntity inputComment = postedComment.getCommentEntity();
        IdObjectEntity inputPostId = postedComment.getPostId();
        return new ResponseEntity<ResponseObjectService>(commentService.editUserComment(inputComment, inputPostId.getId()), HttpStatus.OK);
    }

    // API to delete a comment from a post
    @PutMapping("/deletecomment")
    public ResponseEntity<ResponseObjectService> deletePost(@RequestBody CommentPostRequestEntity postedComment) {
        System.out.println(postedComment); // Log the incoming request
        CommentEntity inputComment = postedComment.getCommentEntity();
        IdObjectEntity inputPostId = postedComment.getPostId();
        return new ResponseEntity<ResponseObjectService>(commentService.deleteUserComment(inputComment, inputPostId.getId()), HttpStatus.OK);
    }

}