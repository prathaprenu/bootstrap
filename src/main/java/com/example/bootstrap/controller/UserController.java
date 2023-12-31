package com.example.bootstrap.controller;

import com.example.bootstrap.exception.ResourceNotFoundException;
import com.example.bootstrap.model.User;
import com.example.bootstrap.repository.UserRepository;
import com.example.bootstrap.security.CurrentUser;
import com.example.bootstrap.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User","id",userPrincipal.getId()));
    }
}
