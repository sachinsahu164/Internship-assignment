package com.sachin.Internship.assignment.controller;

import com.sachin.Internship.assignment.entity.Post;
import com.sachin.Internship.assignment.service.PostService;
import com.sachin.Internship.assignment.service.ViralityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ViralityService viralityService;

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        Post savedPost = postService.createPost(post);
        viralityService.incrementScore(savedPost.getId(), "POST_CREATED");

        return savedPost;
    }
}