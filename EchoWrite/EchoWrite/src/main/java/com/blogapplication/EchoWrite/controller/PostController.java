package com.blogapplication.EchoWrite.controller;

import com.blogapplication.EchoWrite.model.Post;
import com.blogapplication.EchoWrite.model.User;
import com.blogapplication.EchoWrite.service.PostService;
import com.blogapplication.EchoWrite.service.UserService;

import jakarta.servlet.http.HttpSession;

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


   @PostMapping("/create")
public String createPost(@ModelAttribute Post post, HttpSession session) {
    // Retrieve the User object from the session
    User currentUser = (User) session.getAttribute("user");
    
    // Check if the user is logged in
    if (currentUser == null) {
        return "redirect:/login"; // Redirect to login if no user is found in the session
    }

    // Associate the post with the logged-in user
    post.setUser(currentUser);
    
    // Save the post
    postService.createPost(post);
    
    return "redirect:/dashboard"; // Redirect to the dashboard after successful post creation
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
        return "redirect:/dashboard";
    }

    // Route for deleting a post by ID
    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/dashboard";
    }
   
    @GetMapping("/{id}")
    public String viewPostDetails(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id); // Fetch the post by its ID
        model.addAttribute("post", post); // Add the post to the model
        return "post_details"; // Return the view name for the post details page
    }
    

}
