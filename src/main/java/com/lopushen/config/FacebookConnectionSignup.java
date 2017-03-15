package com.lopushen.config;

import com.lopushen.dao.UserRepository;
import com.lopushen.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String execute(Connection<?> connection) {
        User user = userRepository.findByUsername(connection.getDisplayName());

        user.setUsername(connection.getDisplayName());
        user.setPassword(UUID.randomUUID().toString().substring(8));
        userRepository.save(user);
        return user.getUsername();
    }


}
