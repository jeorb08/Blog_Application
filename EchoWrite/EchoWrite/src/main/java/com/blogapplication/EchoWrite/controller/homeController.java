package com.blogapplication.EchoWrite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.blogapplication.EchoWrite.model.User;
import com.blogapplication.EchoWrite.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class homeController {
        @Autowired
        private UserService userService;

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
