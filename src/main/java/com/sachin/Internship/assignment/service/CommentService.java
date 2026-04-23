package com.sachin.Internship.assignment.service;

import com.sachin.Internship.assignment.entity.Comment;
import com.sachin.Internship.assignment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

   public Comment addComment(Comment comment) {
    if (comment.getDepthLevel() > 20) {
        throw new RuntimeException("Depth limit exceeded (max 20)");
    }

    comment.setCreatedAt(LocalDateTime.now());

    return commentRepository.save(comment);
}
}
