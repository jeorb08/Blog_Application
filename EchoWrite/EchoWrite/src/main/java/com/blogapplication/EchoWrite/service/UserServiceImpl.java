package com.blogapplication.EchoWrite.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.EchoWrite.model.User;
import com.blogapplication.EchoWrite.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

        @Autowired
        private UserRepository userRepo;
        @Override
        public User createUser(User user){
                return userRepo.save(user); 
        }
        @Override
        public boolean checkEmail(String email){
                return userRepo.existsByEmail(email);
        }

}
