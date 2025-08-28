package com.jpdev.collaborative_list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jpdev.collaborative_list.entity.UserEntity;
import com.jpdev.collaborative_list.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class DataLoader {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (userRepository.findByUsername("testuser").isEmpty()) {
            UserEntity user = new UserEntity();
            user.setUsername("testuser");
            user.setPassword(passwordEncoder.encode("password123"));
            userRepository.save(user);
        }
    }
}
