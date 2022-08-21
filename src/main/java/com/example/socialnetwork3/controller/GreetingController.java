package com.example.socialnetwork3.controller;

import com.example.socialnetwork3.model.Message;
import com.example.socialnetwork3.model.User;
import com.example.socialnetwork3.service.MessageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;


@Controller
public class GreetingController {

    private final MessageService messageService;

    private GreetingController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String getLogin(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user.getUsername());

        return "hello";
    }

    @GetMapping("/main")
    public String getMain(@AuthenticationPrincipal User user,
                          @ModelAttribute Message message,
                          Model model){
        model.addAttribute("messages", messageService.getAllMessages(user));
        model.addAttribute("user", user.getUsername());

        return "main";
    }
    @PostMapping("/main")
    public String createPost(@RequestParam("file") MultipartFile file,
                             @RequestParam("text") String text,
                             @RequestParam("tag") String tag,
                             @AuthenticationPrincipal User user,
                             @ModelAttribute("message") @Valid Message message,
                             BindingResult bindingResult,

                             Model model) throws IOException {
        if(bindingResult.hasErrors()) {
            model.addAttribute("messages", messageService.getAllMessages(user));
            return "main";
        }

        messageService.createMesssge(text,tag, user,file);

        return "redirect:/main";
    }



}
