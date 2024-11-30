package com.blogapplication.EchoWrite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // This will look for dashboard.html in src/main/resources/templates
    }
}