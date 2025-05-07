


package com.linhtch90.psnbackend.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.linhtch90.psnbackend.entity.CommentEntity;
import com.linhtch90.psnbackend.entity.PostEntity;
import com.linhtch90.psnbackend.entity.ReplyEntity;
import com.linhtch90.psnbackend.repository.CommentRepository;
import com.linhtch90.psnbackend.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private PostService postService;

    @Autowired
    private NotificationService notificationService;

    public ResponseObjectService insertComment(CommentEntity inputComment, String inputPostId) {
        ResponseObjectService responseObj = new ResponseObjectService();
        Optional<PostEntity> optPost = postRepo.findById(inputPostId);
        if (optPost.isEmpty()) {
            responseObj.setStatus("fail");
            responseObj.setMessage("cannot find target post id: " + inputPostId);
            responseObj.setPayload(null);
            return responseObj;
        } else {
            inputComment.setCreatedAt(Instant.now());
            commentRepo.save(inputComment);
            PostEntity targetPost = optPost.get();
            List<CommentEntity> commentList = targetPost.getComment();
            if (commentList == null) {
                commentList = new ArrayList<>();
            }
            commentList.add(inputComment);
            targetPost.setComment(commentList);
            postService.updatePostByComment(targetPost);
            notificationService.createNotification(
                targetPost.getUserId(),
                inputComment.getUserId(),
                inputComment.getUserFullname(),
                targetPost.getId(),
                inputComment.getId(),
                "COMMENT",
                "commented on your post."
            );
            responseObj.setStatus("success");
            responseObj.setMessage("success");
            responseObj.setPayload(inputComment);
            return responseObj;
        }
    }

    public ResponseObjectService getComments(String inputPostId) {
        ResponseObjectService responseObj = new ResponseObjectService();
        Optional<PostEntity> optTargetPost = postRepo.findById(inputPostId);
        if (optTargetPost.isEmpty()) {
            responseObj.setStatus("fail");
            responseObj.setMessage("fail");
            responseObj.setPayload(null);
            return responseObj;
        } else {
            PostEntity targetPost = optTargetPost.get();
            List<CommentEntity> commentList = targetPost.getComment();
            if (commentList.size() > 0) {
                responseObj.setStatus("success");
                responseObj.setMessage("success");
                responseObj.setPayload(commentList);
                return responseObj;
            } else {
                responseObj.setStatus("success");
                responseObj.setMessage("Post id " + inputPostId + " does not have any comment");
                responseObj.setPayload(null);
                return responseObj;
            }
        }
    }
    public ResponseObjectService getAllComments() {
        ResponseObjectService responseObj = new ResponseObjectService();
        responseObj.setPayload(commentRepo.findAll());
        responseObj.setStatus("success");
        responseObj.setMessage("success");
        return responseObj;
    }

    public ResponseObjectService editUserComment(CommentEntity inputComment, String inputPostId) {
        ResponseObjectService responseObj = new ResponseObjectService();
        Optional<PostEntity> optPost = postRepo.findById(inputPostId);
//        Optional<PostEntity> optPost = postRepo.findById("645c8e899dc0394c96ce28d8");
//        Optional<CommentEntity> optPosts = commentRepo.findById("645c8e899dc0394c96ce28d8");

//        System.out.println(optPost+"    "+" timing boss");
//        inputPostId645c8ee59dc0394c96ce28da
        if (optPost.isEmpty()) {
            responseObj.setStatus("fail");
            responseObj.setMessage("cannot find target post id: " + inputPostId);
            responseObj.setPayload(null);
            return responseObj;
        } else {
            inputComment.setCreatedAt(Instant.now());
            commentRepo.save(inputComment);
            PostEntity targetPost = optPost.get();
            List<CommentEntity> commentList = targetPost.getComment();
            if (commentList == null) {
                commentList = new ArrayList<>();
            }
//            System.out.println(inputComment);
//            commentList.set(0, inputComment);
            for (int i = 0; i < commentList.size(); i++) {
                System.out.println(commentList.get(i).getId()+ "  " + inputComment.getId() );
                if (commentList.get(i).getId().equals(inputComment.getId())) {
                    System.out.println("welcome");
                    commentList.set(i, inputComment);
                }else{
                    System.out.println("fails");
                }
            }
            targetPost.setComment(commentList);
            System.out.println("targetPost   "+ commentList);
            postService.updateEditPostByComment(targetPost);
            responseObj.setStatus("success");
            responseObj.setMessage("success");
            responseObj.setPayload(targetPost);
            return responseObj;
        }
    }

    public ResponseObjectService deleteUserComment(CommentEntity inputComment, String inputPostId) {
        ResponseObjectService responseObj = new ResponseObjectService();
        Optional<PostEntity> optPost = postRepo.findById(inputPostId);
//        Optional<PostEntity> optPost = postRepo.findById("645c8e899dc0394c96ce28d8");
//        Optional<CommentEntity> optPosts = commentRepo.findById("645c8e899dc0394c96ce28d8");

//        System.out.println(optPost+"    "+" timing boss");
//        inputPostId645c8ee59dc0394c96ce28da
        if (optPost.isEmpty()) {
            responseObj.setStatus("fail");
            responseObj.setMessage("cannot find target post id: " + inputPostId);
            responseObj.setPayload(null);
            return responseObj;
        } else {
            inputComment.setCreatedAt(Instant.now());
            commentRepo.save(inputComment);
            PostEntity targetPost = optPost.get();
            List<CommentEntity> commentList = targetPost.getComment();
            if (commentList == null) {
                commentList = new ArrayList<>();
            }
//            System.out.println(inputComment);
//            commentList.set(0, inputComment);
            for (int i = 0; i < commentList.size(); i++) {
                System.out.println(commentList.get(i).getId()+ "  " + inputComment.getId() );
                if (commentList.get(i).getId().equals(inputComment.getId())) {
                    System.out.println("welcome 21");
                    commentList.remove(i);
                    System.out.println("Comment removed successfully.");
                }else{
                    System.out.println("fails 2");
                }
            }
            targetPost.setComment(commentList);
            System.out.println("targetPost   "+ commentList);
            postService.updateEditPostByComment(targetPost);
            responseObj.setStatus("success");
            responseObj.setMessage("success");
            responseObj.setPayload(commentList);
            return responseObj;
        }
    }

    // Add reply to a comment
    public ResponseObjectService addReply(String parentCommentId, CommentEntity reply) {
        ResponseObjectService responseObj = new ResponseObjectService();
        Optional<CommentEntity> optParentComment = commentRepo.findById(parentCommentId);
        if (optParentComment.isEmpty()) {
            responseObj.setStatus("fail");
            responseObj.setMessage("Parent comment not found");
            return responseObj;
        }
        CommentEntity parentComment = optParentComment.get();

        // Create ReplyEntity from CommentEntity
        ReplyEntity replyEntity = new ReplyEntity();
        replyEntity.setId(reply.getId());
        replyEntity.setUserId(reply.getUserId());
        replyEntity.setUserFullname(reply.getUserFullname());
        replyEntity.setContent(reply.getContent());
        replyEntity.setCreatedAt(Instant.now());

        parentComment.getReplies().add(replyEntity);
        commentRepo.save(parentComment);

        // Update the PostEntity containing this comment
        Optional<PostEntity> optPost = postRepo.findByCommentId(parentCommentId);
        if (optPost.isPresent()) {
            PostEntity post = optPost.get();
            List<CommentEntity> comments = post.getComment();
            for (int i = 0; i < comments.size(); i++) {
                if (comments.get(i).getId().equals(parentCommentId)) {
                    comments.set(i, parentComment);
                    break;
                }
            }
            post.setComment(comments);
            postService.updatePostByComment(post);
        }
        notificationService.createNotification(
            parentComment.getUserId(),
            reply.getUserId(),
            reply.getUserFullname(),
            null,
            parentComment.getId(),
            "REPLY",
            "replied to your comment."
        );
        // Notify post owner if different from comment owner
        Optional<PostEntity> optPost1 = postRepo.findByCommentId(parentCommentId);
        if (optPost1.isPresent() && !optPost1.get().getUserId().equals(parentComment.getUserId())) {
            notificationService.createNotification(
                optPost1.get().getUserId(),
                reply.getUserId(),
                reply.getUserFullname(),
                optPost1.get().getId(),
                parentComment.getId(),
                "REPLY",
                "replied to a comment on your post."
            );
        }

        responseObj.setStatus("success");
        responseObj.setMessage("Reply added successfully");
        responseObj.setPayload(parentComment);
        return responseObj;
    }

    // Edit reply
    public ResponseObjectService editReply(CommentEntity reply) {
        ResponseObjectService responseObj = new ResponseObjectService();
        Optional<CommentEntity> optReply = commentRepo.findById(reply.getId());
        if (optReply.isEmpty()) {
            responseObj.setStatus("fail");
            responseObj.setMessage("Reply not found");
            return responseObj;
        }
        CommentEntity existingReply = optReply.get();
        existingReply.setContent(reply.getContent());
        commentRepo.save(existingReply);
        responseObj.setStatus("success");
        responseObj.setMessage("Reply updated successfully");
        responseObj.setPayload(existingReply);
        return responseObj;
    }

    // Delete reply from a comment
    public ResponseObjectService deleteReply(String parentCommentId, String replyId) {
        ResponseObjectService responseObj = new ResponseObjectService();
        Optional<CommentEntity> optParentComment = commentRepo.findById(parentCommentId);
        if (optParentComment.isEmpty()) {
            responseObj.setStatus("fail");
            responseObj.setMessage("Parent comment not found");
            return responseObj;
        }
        CommentEntity parentComment = optParentComment.get();
        parentComment.getReplies().removeIf(reply -> reply.getId().equals(replyId));
        commentRepo.save(parentComment);
        commentRepo.deleteById(replyId);
        responseObj.setStatus("success");
        responseObj.setMessage("Reply deleted successfully");
        return responseObj;
    }

}
