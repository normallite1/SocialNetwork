package com.example.socialnetwork3.controller;


import com.example.socialnetwork3.model.User;
import com.example.socialnetwork3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrController {

    @Autowired
    private UserService userService;

    @GetMapping("/registr")
    public String registr(@ModelAttribute("user") User user){

        return "registr";
    }

    @PostMapping("/registr")
    public String addUser(@ModelAttribute("user") @Valid User user,
                          BindingResult bindingResult,
                          Model model){
        if(bindingResult.hasErrors())
            return "registr";
        if(!userService.addUser(user)){
            model.addAttribute("message", "User exists");
            return "registr";
        }
        model.addAttribute("message","We send registration link in your email" );
        return "/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivate = userService.activateUser(code);

        if (isActivate){
            model.addAttribute("message", "User activated");
        } else {
            model.addAttribute("message", "User not activated");
        }

        return "login";
    }
}
