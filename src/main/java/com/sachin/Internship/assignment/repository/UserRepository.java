package com.sachin.Internship.assignment.repository;

import com.sachin.Internship.assignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
