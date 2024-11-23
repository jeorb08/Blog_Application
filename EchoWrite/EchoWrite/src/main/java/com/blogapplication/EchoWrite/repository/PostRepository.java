package com.blogapplication.EchoWrite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogapplication.EchoWrite.model.Post;



@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    
}
