package com.MindHub.HomeBanking.dtos;

import com.MindHub.HomeBanking.models.Account;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {
    private Long id;
    private String number;
    private Double balance;
    private LocalDate creationDate;
    private Set<TransactionDTO> transactions;
    public AccountDTO(Account account){
        this.id = account.getId();
        this.number = account.getNumber();
        this.balance = account.getBalance();
        this.creationDate = account.getCreationDate();
        this.transactions = account.getTransactionSet().stream().map(transaction -> new TransactionDTO(transaction)).collect(Collectors.toSet());
    }
    public Long getId() {
        return id;
    }
    public String getNumberAcc() {
        return number;
    }
    public Double getBalanceAcc() {
        return balance;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public Set<TransactionDTO> getTransactions() {
        return transactions;
    }
}
