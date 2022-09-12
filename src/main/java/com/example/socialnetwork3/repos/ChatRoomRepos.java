package com.example.socialnetwork3.repos;

import com.example.socialnetwork3.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepos extends JpaRepository<ChatRoom, Long> {
}
