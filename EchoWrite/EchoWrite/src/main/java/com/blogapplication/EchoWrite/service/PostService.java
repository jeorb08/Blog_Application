package com.blogapplication.EchoWrite.service;

import com.blogapplication.EchoWrite.model.Post;
import com.blogapplication.EchoWrite.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void createPost(Post post) {
        postRepository.save(post);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public void updatePost(Long id, Post updatedPost) {
        Post existingPost = getPostById(id);
        existingPost.setPostTitle(updatedPost.getPostTitle());
        existingPost.setPostDescription(updatedPost.getPostDescription());
        existingPost.setUser(updatedPost.getUser()); // Update the user
        postRepository.save(existingPost);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
