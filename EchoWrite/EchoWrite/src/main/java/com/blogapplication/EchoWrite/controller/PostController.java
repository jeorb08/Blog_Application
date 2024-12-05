package com.blogapplication.EchoWrite.controller;

import com.blogapplication.EchoWrite.model.Post;
import com.blogapplication.EchoWrite.model.User;
import com.blogapplication.EchoWrite.service.PostService;
import com.blogapplication.EchoWrite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService; // To handle user-related operations.

    // Route for displaying the "Create Post" form
    @GetMapping("/create")
    public String showCreatePostForm(Model model) {
    model.addAttribute("post", new Post()); // Adds a new Post object
    return "create_post";
    }


    // Route for handling the form submission
    @PostMapping("/create")
    public String createPost(@ModelAttribute Post post) {
        // Fetch the default user with ID 1
        User defaultUser = userService.getUserById(1L); // Replace with appropriate method to fetch the user
        post.setUser(defaultUser); // Associate the post with the default user
        postService.createPost(post); // Save the post
        return "redirect:/dashboard";
    }
    

    // Route for editing a post by ID
    @GetMapping("/edit/{id}")
    public String showEditPostForm(@PathVariable Long id, Model model) {
        Post existingPost = postService.getPostById(id);
        model.addAttribute("post", existingPost);
        model.addAttribute("users", userService.getAllUsers()); // Include users for reassignment if needed
        return "edit_post";
    }

    // Route for updating a post by ID
    @PostMapping("/edit/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post, @RequestParam("userId") Long userId) {
        User user = userService.getUserById(userId);
        post.setUser(user); // Update the user for the post
        postService.updatePost(id, post);
        return "redirect:/posts";
    }

    // Route for deleting a post by ID
    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }
   
    @GetMapping("/{id}")
    public String viewPostDetails(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id); // Fetch the post by its ID
        model.addAttribute("post", post); // Add the post to the model
        return "post_details"; // Return the view name for the post details page
    }
    

}
