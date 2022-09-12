package com.example.socialnetwork3.controller;

import com.example.socialnetwork3.model.ChatMessage;
import com.example.socialnetwork3.model.User;
import com.example.socialnetwork3.service.ChatRoomService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/ws")
public class ChatController {

    private final ChatRoomService chatRoomService;

    public ChatController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @GetMapping()
    public String getChat(@AuthenticationPrincipal User userCurrent, Model model){
        model.addAttribute("userCurrent", userCurrent);
        model.addAttribute("chatrooms", chatRoomService.getAllChatRoom());
        return "chat";
    }
    @PostMapping()
    public String createChatRoom(@RequestParam String chatroom){
        chatRoomService.createChatRoom(chatroom);
        return "redirect:/ws";
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
