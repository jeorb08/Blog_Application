package com.blogapplication.EchoWrite.repository;

import com.blogapplication.EchoWrite.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
