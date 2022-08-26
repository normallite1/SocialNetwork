package com.example.socialnetwork3.service;

import com.example.socialnetwork3.model.Message;
import com.example.socialnetwork3.model.User;
import com.example.socialnetwork3.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class MessageService {
    private final MessageRepo messageRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }


    public void createMesssge(String text, String tag, User user, MultipartFile file) throws IOException {
        Message message = new Message();
        if(file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            message.setFilename(resultFileName);
        }
        message.setUser(user);
        message.setText(text);
        message.setTag(tag);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MMM-dd HH:mm:ss", Locale.UK);
        LocalDateTime time = LocalDateTime.now();
        message.setDataTime(formatter.format(time));

        messageRepo.save(message);
    }

    public Set<Message> getAllMessagesUser(User user){
        return messageRepo.findMessageWhereUser(user);
    }

    public void deleteMessage(Long id) {
        messageRepo.deleteById(id);
    }

    public Message getOneMessage(Long id) {
        return messageRepo.getById(id);
    }
    public void updateMessage(Long id, String text, String tag, MultipartFile file) throws IOException {
        Message message = getOneMessage(id);
        if(file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            message.setFilename(resultFileName);
        }
        message.setText(text);
        message.setTag(tag);
        messageRepo.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepo.findAll();
    }
}
