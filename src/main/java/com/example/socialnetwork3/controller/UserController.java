package com.example.socialnetwork3.controller;

import com.example.socialnetwork3.model.User;
import com.example.socialnetwork3.repos.UserRepos;
import com.example.socialnetwork3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepos userRepos;

    @Autowired
    private UserService userService;

//    @GetMapping()
//    public String getUserList(Model model){
//        model.addAttribute("users", userRepos.findAll());
//
//        return "userList";
//    }
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/{id}/edit")
//    public String userEdit( @PathVariable Long id, Model model){
//        model.addAttribute("user", userRepos.getOne(id));
//        model.addAttribute("roles", Roles.values());
//
//        return "userEdit";
//    }
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @PostMapping("/{id}/edit")
//    public String userSave(@ModelAttribute("user") User user){
//
//        userRepos.save(user);
//
//        return "redirect:/user";
//    }
    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal User user,
                             Model model){
        model.addAttribute("user", user.getUsername());
        model.addAttribute("useru", user);


        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal User user,
                                @RequestParam("email") String email,
                                Model model){
        if(userService.updateProfile(user, email)){
            model.addAttribute("message", "Activate your profile, visit link send your email");
            return "login";
        } else {
            model.addAttribute("message", "Error");
            return "profile";
        }

    }
}
