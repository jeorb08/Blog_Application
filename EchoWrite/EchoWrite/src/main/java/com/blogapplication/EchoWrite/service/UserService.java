package com.blogapplication.EchoWrite.service;
import org.springframework.stereotype.Service;

import com.blogapplication.EchoWrite.model.User;


@Service
public interface UserService {
   
   public void removeSessionMessage();



public  User createUser(User user) ;
        
} 
