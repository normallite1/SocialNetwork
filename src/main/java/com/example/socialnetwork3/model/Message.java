package com.example.socialnetwork3.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Text should not be empty")
    @Size(min=2, max=255, message = "Text should be between 2 and 255 char")
    private String text;
    @NotEmpty(message = "Tag should not be empty")
    @Size(min=2, max=30, message = "Tag should be between 2 and 30 char")
    private String tag;
    private String dataTime;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String filename;

    public Message(String text, String tag, String dataTime, User user) {

        this.text = text;
        this.tag = tag;
        this.dataTime = dataTime;
        this.user = user;
    }
    public Message(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}

