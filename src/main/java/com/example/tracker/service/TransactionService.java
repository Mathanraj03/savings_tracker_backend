package com.example.tracker.service;

import com.example.tracker.dto.TransactionDTO;
import com.example.tracker.model.Transaction;
import com.example.tracker.model.TransactionType;
import com.example.tracker.model.User;
import com.example.tracker.repository.TransactionRepository;
import com.example.tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private UserRepository userRepo;

    public List<Transaction> getAllByUser(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return transactionRepo.findByUser(user);
    }

    public Transaction add(TransactionDTO dto, String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Transaction t = new Transaction();
        t.setDate(dto.getDate());
        t.setAmount(dto.getAmount());
        t.setCategory(dto.getCategory());
        t.setType(TransactionType.valueOf(dto.getType().toUpperCase()));
        t.setUser(user);
        return transactionRepo.save(t);
    }

    public Transaction update(Long id, TransactionDTO dto, String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Transaction t = transactionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        if (!t.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        t.setDate(dto.getDate());
        t.setAmount(dto.getAmount());
        t.setCategory(dto.getCategory());
        t.setType(TransactionType.valueOf(dto.getType().toUpperCase()));
        return transactionRepo.save(t);
    }

    public void delete(Long id, String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Transaction t = transactionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        if (!t.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        transactionRepo.delete(t);
    }
}
