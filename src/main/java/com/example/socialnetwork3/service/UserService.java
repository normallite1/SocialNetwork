package com.example.socialnetwork3.service;

import com.example.socialnetwork3.model.Roles;
import com.example.socialnetwork3.model.User;
import com.example.socialnetwork3.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final UserRepos userRepos;
    private final MailSender mailSender;

    public UserService(UserRepos userRepos, MailSender mailSender) {
        this.userRepos = userRepos;
        this.mailSender = mailSender;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepos.findByUsername(username);
    }
    public boolean addUser(User user){
        User userFromDB = userRepos.findByUsername(user.getUsername());

        if(userFromDB != null){
            return false;
        }
        user.setRoles(Collections.singleton(Roles.USER));
        user.setActivatoreCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepos.save(user);

        sendMessage(user);

        return true;
    }

    private void sendMessage(User user) {
        if(!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "Hello, %s! \n "+
                            "Welcome to Social. Please, visit next link http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivatoreCode()
            );

           mailSender.send(user.getEmail(), "Activation code", message);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepos.findByActivatoreCode(code);

        if(user == null){
            return false;
        }
        user.setActivatoreCode(null);
        user.setActive(true);
        userRepos.save(user);

        return true;
    }

    public boolean updateProfile(User user, String email) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));


        if(isEmailChanged) {
            user.setEmail(email);

            if(!StringUtils.isEmpty(email)) {
                user.setActivatoreCode(UUID.randomUUID().toString());
            }
            user.setActive(false);
            sendMessage(user);
            userRepos.save(user);
        } else return false;

        return true;
    }

    public User getUser(Long id) {
        return userRepos.getById(id);
    }
}
