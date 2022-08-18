package com.example.socialnetwork3.repos;

import com.example.socialnetwork3.model.Message;
import com.example.socialnetwork3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface MessageRepo extends JpaRepository<Message, Long> {

    @Query("SELECT i FROM Message i WHERE i.user = :user")
    Set<Message> findMessageWhereUser (@Param("user") User user);
}
