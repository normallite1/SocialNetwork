package com.example.socialnetwork3.controller;

import com.example.socialnetwork3.controllerService.MessageContService;
import com.example.socialnetwork3.model.Message;
import com.example.socialnetwork3.service.MessageService;
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
    private final MessageService messageService;

    public MessageController(MessageContService messageContService, MessageService messageService) {
        this.messageContService = messageContService;
        this.messageService = messageService;
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
                              @RequestParam String text,
                              @RequestParam String tag,
                              @RequestParam(value = "file") MultipartFile file,
                              @ModelAttribute("message") @Valid Message message,
                              BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors()){
            return "messageEdit";
        }
        messageService.updateMessage(id,text, tag, file);
        return "redirect:/main";
    }
}
