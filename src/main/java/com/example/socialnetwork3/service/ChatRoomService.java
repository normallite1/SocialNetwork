package com.example.socialnetwork3.service;

import com.example.socialnetwork3.model.ChatRoom;
import com.example.socialnetwork3.repos.ChatRoomRepos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService {
    private final ChatRoomRepos chatRoomRepos;

    public ChatRoomService(ChatRoomRepos chatRoomRepos) {
        this.chatRoomRepos = chatRoomRepos;
    }

    public void createChatRoom(String chatroom) {
        ChatRoom room = new ChatRoom();
        room.setName(chatroom);
        chatRoomRepos.save(room);
    }

    public List<ChatRoom> getAllChatRoom() {
        return chatRoomRepos.findAll();
    }
}
