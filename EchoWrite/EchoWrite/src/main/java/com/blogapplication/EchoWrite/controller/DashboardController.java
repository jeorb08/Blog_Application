package com.blogapplication.EchoWrite.controller;

import com.blogapplication.EchoWrite.model.Post;
import com.blogapplication.EchoWrite.model.User;
import com.blogapplication.EchoWrite.service.PostService;
import jakarta.servlet.http.HttpSession;
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
    public String dashboard(HttpSession session, Model model) {
        // Get the current user from the session
        User currentUser = (User) session.getAttribute("user");

        // If no user is logged in, redirect to login
        if (currentUser == null) {
            return "redirect:/login";
        }

        // Add the user to the model
        model.addAttribute("user", currentUser);

        // Fetch all posts and add them to the model
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);

        return "dashboard";
    }
}
