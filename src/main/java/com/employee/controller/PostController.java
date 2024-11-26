package com.employee.controller;

import com.employee.entity.Post;
import com.employee.repository.CommentRepository;
import com.employee.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    //create constructor for both
    public PostController(PostRepository postRepository, CommentRepository commentRepository){
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }
    @PostMapping
    public String createPost(
            @RequestBody Post post

    ){
        postRepository.save(post);
        return "Post Created Successfully";

    }
    @DeleteMapping
    public void deletePost(){
        postRepository.deleteById(1L);
    }

}


