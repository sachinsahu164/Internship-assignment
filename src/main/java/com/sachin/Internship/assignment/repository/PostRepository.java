package com.sachin.Internship.assignment.repository;

import com.sachin.Internship.assignment.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
