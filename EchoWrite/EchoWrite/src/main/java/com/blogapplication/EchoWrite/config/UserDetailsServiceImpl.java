package com.blogapplication.EchoWrite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogapplication.EchoWrite.model.User;
import com.blogapplication.EchoWrite.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private  UserRepository userRepo;
    
    @Autowired
private PasswordEncoder passwordEncoder;

    
    
    public User createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("User");
        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userRepo.findByEmail(email);
        if(user!=null){
                return new CustomUser(user); 
        }
        throw new UsernameNotFoundException("User not available");
}
}
