package com.blogapplication.EchoWrite.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.EchoWrite.model.Post;
import com.blogapplication.EchoWrite.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
        
        @Autowired
        private PostRepository postRepository;

        public Post savepost(Post post){
                post.setLikeCount(0);
                post.setDate(new Date());

             return   postRepository.save(post);

        }

}
