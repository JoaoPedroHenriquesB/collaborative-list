package com.jpdev.collaborative_list.controller;

import com.jpdev.collaborative_list.dto.LoginRequest;
import com.jpdev.collaborative_list.dto.RegisterRequest;
import com.jpdev.collaborative_list.entity.UserEntity;
import com.jpdev.collaborative_list.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public LoginController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("User logged in successfully");
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody RegisterRequest registerRequest) {
        UserEntity registeredUser = userService.registerUser(registerRequest.getUsername(), registerRequest.getPassword());
        return ResponseEntity.ok(registeredUser);
    }
}
