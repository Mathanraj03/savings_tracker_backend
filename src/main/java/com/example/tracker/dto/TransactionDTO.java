package com.example.tracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDTO {
    private LocalDate date;
    private BigDecimal amount;
    private String type;
    private String category;

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
