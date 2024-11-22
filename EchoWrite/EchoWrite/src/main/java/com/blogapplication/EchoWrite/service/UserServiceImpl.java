package com.blogapplication.EchoWrite.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.blogapplication.EchoWrite.model.User;
import com.blogapplication.EchoWrite.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

        @Autowired
        private UserRepository userRepo;
        @Autowired
	private BCryptPasswordEncoder getPasswordEncoder;
        @Override
        public User createUser(User user){
                String password= getPasswordEncoder.encode(user.getPassword());
		user.setPassword(password);
		user.setRole("ROLE_USER");
                return userRepo.save(user); 

                
        }
        @Override
        public boolean checkEmail(String email){
                return userRepo.existsByEmail(email);
        }
        @SuppressWarnings("null")
        @Override
	public void removeSessionMessage() {

		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg");
	}


}
