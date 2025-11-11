package com.example.tracker.service;

import com.example.tracker.dto.UserDTO;
import com.example.tracker.model.User;
import com.example.tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User register(UserDTO dto) {
        Optional<User> existing = repo.findByEmail(dto.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("Email already registered!");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return repo.save(user);
    }

    public User login(String email, String password) {
        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }
        return user;
    }

    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }
}
