package com.MindHub.HomeBanking.dtos;

import com.MindHub.HomeBanking.models.Transaction;
import com.MindHub.HomeBanking.models.TransactionType;

import java.time.LocalDateTime;

public class TransactionDTO {
    private Long id;
    private TransactionType type;
    private Double amount;
    private String description;
    private LocalDateTime dateTime;
    public TransactionDTO(){

    }
    public TransactionDTO(Transaction transaction) {
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
        this.dateTime = transaction.getDateTime();
        this.id = transaction.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
