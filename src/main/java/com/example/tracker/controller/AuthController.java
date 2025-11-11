package com.example.tracker.controller;

import com.example.tracker.dto.UserDTO;
import com.example.tracker.model.User;
import com.example.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody UserDTO dto) {
        return userService.register(dto);
    }

    @PostMapping("/login")
    public User login(@RequestBody UserDTO dto) {
        return userService.login(dto.getEmail(), dto.getPassword());
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
