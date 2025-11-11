package com.example.tracker.controller;

import com.example.tracker.dto.TransactionDTO;
import com.example.tracker.model.Transaction;
import com.example.tracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{email}")
    public List<Transaction> getAll(@PathVariable("email") String email) {
        return transactionService.getAllByUser(email);
    }

    @PostMapping("/{email}")
    public Transaction add(@PathVariable("email") String email, @RequestBody TransactionDTO dto) {
        return transactionService.add(dto, email);
    }

    @PutMapping("/{id}/{email}")
    public Transaction update(@PathVariable("id") Long id,
                              @PathVariable("email") String email,
                              @RequestBody TransactionDTO dto) {
        return transactionService.update(id, dto, email);
    }

    @DeleteMapping("/{id}/{email}")
    public void delete(@PathVariable("id") Long id, @PathVariable("email") String email) {
        transactionService.delete(id, email);
    }
}
