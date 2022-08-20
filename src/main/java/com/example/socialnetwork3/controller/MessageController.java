package com.example.socialnetwork3.controller;

import com.example.socialnetwork3.controllerService.MessageContService;
import com.example.socialnetwork3.model.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/message")
public class MessageController {

    private final MessageContService messageContService;

    public MessageController(MessageContService messageContService) {
        this.messageContService = messageContService;
    }

    @GetMapping("/{id}/delete")
    public String deleteMessage(@PathVariable Long id){
        messageContService.deleteOneMessage(id);
        return "redirect:/main";
    }
    @GetMapping("/{id}/edit")
    public String editMessage(@PathVariable Long id, Model model){
        model.addAttribute("message" , messageContService.getOneMessage(id));
        return "messageEdit";
    }
    @PostMapping("/{id}/edit")
    public String editMessage(@PathVariable Long id,
                              @ModelAttribute("message") @Valid Message message,
                              BindingResult bindingResult,
                              @RequestParam(value = "file") MultipartFile file) throws IOException {
        if(bindingResult.hasErrors()){
            return "messageEdit";
        }
        messageContService.updateMessage(id,message, file);
        return "redirect:/main";
    }
}
