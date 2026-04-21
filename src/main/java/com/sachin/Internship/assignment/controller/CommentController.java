package com.sachin.Internship.assignment.controller;

import com.sachin.Internship.assignment.entity.Comment;
import com.sachin.Internship.assignment.service.CommentService;
import com.sachin.Internship.assignment.service.ViralityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

    private final CommentService commentService;
    private final ViralityService viralityService;

    // ✅ Constructor Injection
    public CommentController(CommentService commentService,
                             ViralityService viralityService) {
        this.commentService = commentService;
        this.viralityService = viralityService;
    }

    @PostMapping("/{postId}/comments")
    public Comment addComment(@PathVariable Long postId,
                              @RequestBody Comment comment) {
        comment.setPostId(postId);
        viralityService.incrementScore(postId, "COMMENT");
        return commentService.addComment(comment);
    }

    @PostMapping("/{postId}/like")
    public String likePost(@PathVariable Long postId) {
        System.out.println("LIKE HIT");
        viralityService.incrementScore(postId, "LIKE");
        return "Liked";
    }

    @GetMapping("/{postId}/score")
    public String getScore(@PathVariable Long postId) {
        return viralityService.getScore(postId);
    }
}