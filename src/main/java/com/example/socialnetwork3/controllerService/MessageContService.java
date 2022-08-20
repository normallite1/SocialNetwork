package com.example.socialnetwork3.controllerService;

import com.example.socialnetwork3.model.Message;
import com.example.socialnetwork3.repos.MessageRepo;
import com.example.socialnetwork3.service.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MessageContService {

    private final MessageRepo messageRepo;
    private final MessageService messageService;


    public MessageContService(MessageService messageService, MessageRepo messageRepo) {
        this.messageService = messageService;
        this.messageRepo = messageRepo;
    }

    public Message getOneMessage(Long id) {
         return messageService.getOneMessage(id);
    }

    public void deleteOneMessage(Long id) {
        messageRepo.deleteById(id);
    }

    public void updateMessage(Long id, Message message, MultipartFile file) throws IOException {
       messageService.updateMessage(id,message, file);
    }
}
