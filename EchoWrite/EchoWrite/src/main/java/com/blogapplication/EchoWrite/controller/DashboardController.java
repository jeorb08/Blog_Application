package com.blogapplication.EchoWrite.controller;

import com.blogapplication.EchoWrite.model.Post;
import com.blogapplication.EchoWrite.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private PostService postService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Fetch all posts
        List<Post> posts = postService.getAllPosts();

        // Add posts to the model
        model.addAttribute("posts", posts);

        return "dashboard"; // This will look for dashboard.html in src/main/resources/templates
    }
}
