package com.example.socialnetwork3.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chat_room")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.EAGER)
    private List<ChatMessage> chatMessageList;

    public ChatRoom(){

    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChatMessage> getChatMessageList() {
        return chatMessageList;
    }

    public void setChatMessageList(List<ChatMessage> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }
}
