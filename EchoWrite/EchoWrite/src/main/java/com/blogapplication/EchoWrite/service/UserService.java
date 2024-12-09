package com.blogapplication.EchoWrite.service;

import com.blogapplication.EchoWrite.model.User;
import com.blogapplication.EchoWrite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Login method
    public Optional<User> login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);  // Ensure passwords are hashed in real apps
    }

    // Register user
    public void registerUser(User user) {
        userRepository.save(user);
    }

    // Get user by ID
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);  // This will return an Optional<User>
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();  // Retrieves all users from the database
    }
}
