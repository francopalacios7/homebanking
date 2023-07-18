package com.MindHub.HomeBanking.dtos;

import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.models.AccountType;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {
    private Long id;
    private String number;
    private double balance;
    private LocalDate creationDate;
    private Set<TransactionDTO> transactions;
    private boolean isActive;
    private AccountType type;
    public AccountDTO(Account account){
        this.id = account.getId();
        this.number = account.getNumber();
        this.balance = account.getBalance();
        this.creationDate = account.getCreationDate();
        this.transactions = account.getTransactionSet().stream().map(transaction -> new TransactionDTO(transaction)).collect(Collectors.toSet());
        this.isActive = account.isActive();
        this.type = account.getAccountType();
    }
    public Long getId() {
        return id;
    }
    public String getNumber() {
        return number;
    }
    public Double getBalance() {
        return balance;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public Set<TransactionDTO> getTransactions() {
        return transactions;
    }
    public boolean isActive() {return isActive;}
    public AccountType getType() {return type;}
}
