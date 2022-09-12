package com.example.socialnetwork3.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "chat_Messages")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Transient
    private MessageType type;
    private String content;
    private String sender;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name="chat_room_id")
    private ChatRoom chatRoom;

    public enum MessageType implements GrantedAuthority {
        CHAT,
        JOIN,
        LEAVE;

        @Override
        public String getAuthority() {
            return name();
        }
    }
    public MessageType getType() {
        return type;
    }
    public void setType(MessageType type) {
        this.type = type;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }
    public Long getId() {
        return id;
    }
}
