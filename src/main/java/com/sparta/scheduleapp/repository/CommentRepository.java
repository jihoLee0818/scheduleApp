package com.sparta.scheduleapp.repository;

import com.sparta.scheduleapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}