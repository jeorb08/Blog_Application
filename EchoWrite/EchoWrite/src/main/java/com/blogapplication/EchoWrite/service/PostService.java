package com.blogapplication.EchoWrite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.EchoWrite.model.Post;
import com.blogapplication.EchoWrite.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public class PostService {
    Post savepost(Post post);
}