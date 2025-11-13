package com.example.casestudy.controller;

import com.example.casestudy.model.Users;
import com.example.casestudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login()
    {
        return "login";
    }
    @GetMapping("/register")
    public String showFormRegister(Model model)
    {
        model.addAttribute("user", new Users());
        return "register";
    }
    @PostMapping("/register")
    public String registerUser(Users user){
        userService.register(user);
        return "redirect:/login";
    }
    @GetMapping("/home")
    public String home() {
        return "home"; // home.html
    }

}
