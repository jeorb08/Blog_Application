package com.blogapplication.EchoWrite.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.blogapplication.EchoWrite.model.User;
import com.blogapplication.EchoWrite.repository.UserRepository;
import com.blogapplication.EchoWrite.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class homeController {
        @Autowired
        private UserService userService;

        @Autowired
	private UserRepository userRepo;

       


        @GetMapping("/")
        public String index(){
                return "index";
        }
        @GetMapping("/signin")
        public String login(){
                return "login";
        }
        

        @GetMapping("/registration")
        public String registration(){
                return "registration";
        }
        @GetMapping("/user/profile")
	public String profile(Principal p, Model m) {
		String email = p.getName();
		User user = UserRepository.findByEmail(email);
		m.addAttribute("user", user);
		return "profile";
	}
        @GetMapping("/user/home")
	public String home() {
		return "home";
	}

        

        @PostMapping("/createUser")
        public String createuser(@ModelAttribute User user, HttpSession session){

                //System.out.println(user);
                boolean f= userService.checkEmail(user.getEmail());
                if(f){
                        session.setAttribute("msg", "Email id already Exist");
                }
                else{
                        User userdtl = userService.createUser(user);
                if(userdtl!=null){
                        session.setAttribute("msg", "Registration Sucessfully done");     
                } else{
                        session.setAttribute("msg", "Error!..Something Wrong");
                }
        }

                return "redirect:/registration";

        

                
        }
}
