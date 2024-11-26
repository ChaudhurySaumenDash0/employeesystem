package com.employee.controller;

import com.employee.entity.Comment;
import com.employee.entity.Post;
import com.employee.repository.CommentRepository;
import com.employee.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class commentController {
    private PostRepository  postRepository;
    private CommentRepository commentRepository;
    public commentController(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }
    @PostMapping
    public String createComment(
            @RequestBody Comment comment,
            @RequestParam long postId
    ){
        Post post=postRepository.findById(postId).get();
        comment.setPost(post);
        commentRepository.save(comment);
        return "Comment created successfully";

    }
}
