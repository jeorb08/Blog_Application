package com.blogapplication.EchoWrite.controller;

import com.blogapplication.EchoWrite.model.User;
import com.blogapplication.EchoWrite.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Serve the registration page
    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";  // Return the name of the register.html view
    }

    // Registration endpoint
    @PostMapping("/register")
    public String registerUser(@RequestParam String fullName, @RequestParam String email,
                               @RequestParam String password, @RequestParam String address) {
        // Create a new user
        User newUser = new User();
        newUser.setFullName(fullName);
        newUser.setEmail(email);
        newUser.setPassword(password);  // In a real-world app, hash the password
        newUser.setAddress(address);
        newUser.setRole("USER");  // Default role as USER

        // Save the user
        userService.registerUser(newUser);

        // Redirect to login page after registration
        return "redirect:/login";  // Redirecting to login page after successful registration
    }
}
