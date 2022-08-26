package com.example.socialnetwork3.controller;

import com.example.socialnetwork3.model.User;
import com.example.socialnetwork3.service.MessageService;
import com.example.socialnetwork3.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    public final MessageService messageService;
    private final UserService userService;

    public UserController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

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
    @GetMapping("{id}")
    public String getUser(@AuthenticationPrincipal User userCurrent,
                          @PathVariable Long id, Model model){
        User user = userService.getUser(id);

        model.addAttribute("userChannel", user);
        model.addAttribute("userCurrent", userCurrent);
        model.addAttribute("messages", messageService.getAllMessagesUser(user));
        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());
       return "userInfo";
    }
    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal User userCurrent,
                             Model model){

        model.addAttribute("userCurrent", userCurrent);


        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal User userCurrent,
                                @RequestParam("email") String email,
                                Model model){
        if(userService.updateProfile(userCurrent, email)){
            model.addAttribute("message", "Activate your profile, visit link send your email");
            return "login";
        } else {
            model.addAttribute("message", "Error");
            return "profile";
        }

    }
}
