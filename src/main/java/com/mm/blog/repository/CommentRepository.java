package com.mm.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mm.blog.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
