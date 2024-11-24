package com.blogapplication.EchoWrite.service;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public User createUser(User user) {

		
	
		User newuser = userRepo.save(user);

		return newuser;
	}
	
	@SuppressWarnings("null")
        @Override
	public void removeSessionMessage() {

		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg");
	}

       


        

}







