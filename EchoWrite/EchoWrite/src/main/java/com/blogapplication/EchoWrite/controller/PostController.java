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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    // Directory to store uploaded images
    private final String uploadDir = "src/main/resources/static/uploads/";

    // Route for displaying the "Create Post" form
    @GetMapping("/create")
    public String showCreatePostForm(Model model) {
        model.addAttribute("post", new Post()); // Adds a new Post object
        return "create_post"; // View for creating a post
    }

    // Route for creating a post
    @PostMapping("/create")
    public String createPost(@ModelAttribute Post post, 
                             @RequestParam(value = "image", required = false) MultipartFile image, 
                             HttpSession session) {
        // Retrieve the User object from the session
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            return "redirect:/login"; // Redirect to login if user is not authenticated
        }

        try {
            // Handle image upload
            if (!image.isEmpty()) {
                String imageName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                Path imagePath = Paths.get(uploadDir, imageName);
                Files.createDirectories(imagePath.getParent()); // Ensure the directory exists
                Files.write(imagePath, image.getBytes());

                // Save the image file name in the post
                post.setImageName(imageName);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "error"; // Return an error page or message in case of failure
        }

        // Associate the post with the logged-in user
        post.setUser(currentUser);

        // Save the post to the database
        postService.createPost(post);

        return "redirect:/dashboard"; // Redirect to the dashboard
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
        Optional<User> userOptional = userService.getUserById(userId); // Get user by ID

        if (userOptional.isPresent()) {
            User user = userOptional.get(); // Unwrap the Optional
            post.setUser(user); // Set the user for the post
            postService.updatePost(id, post); // Update the post in the database
        } else {
            // Handle the case where the user is not found
            // You could add an error message or redirect to an error page
            return "error"; // Example: Redirect to an error page
        }

        return "redirect:/dashboard"; // Redirect to the dashboard after updating
    }

    // Route for deleting a post by ID
    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/dashboard";
    }

    // Route for viewing post details
    @GetMapping("/{id}")
    public String viewPostDetails(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id); // Fetch the post by its ID
        model.addAttribute("post", post); // Add the post to the model
        return "post_details"; // Return the view name for the post details page
    }
}
