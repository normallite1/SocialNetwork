package com.example.socialnetwork3.repos;

import com.example.socialnetwork3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepos extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByActivatoreCode(String code);
}
