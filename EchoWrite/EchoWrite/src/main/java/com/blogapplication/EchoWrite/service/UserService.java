package com.blogapplication.EchoWrite.service;
import com.blogapplication.EchoWrite.model.User;


public interface UserService {
   public User createUser(User user);
  
   public void removeSessionMessage();

public boolean checkEmail(String email);
        
} 
