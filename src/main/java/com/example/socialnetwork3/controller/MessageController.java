package com.example.socialnetwork3.controller;

import com.example.socialnetwork3.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/{id}/delete")
    public String deleteMessage(@PathVariable Long id){
        messageService.deleteMessage(id);
        return "redirect:/main";
    }
}
