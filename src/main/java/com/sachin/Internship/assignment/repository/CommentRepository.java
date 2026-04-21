package com.sachin.Internship.assignment.repository;

import com.sachin.Internship.assignment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
