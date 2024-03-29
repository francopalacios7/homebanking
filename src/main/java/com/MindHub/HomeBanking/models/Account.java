package com.MindHub.HomeBanking.models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String number;
    private double balance;
    private LocalDate creationDate;
    private boolean isActive;
    private AccountType accountType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;
    @OneToMany(mappedBy="account", fetch=FetchType.EAGER)
    Set<Transaction> transactionSet = new HashSet<>();
    public Account() { }
    public Account(String number, double balance, LocalDate date, boolean isActive, AccountType type){
        this.number = number;
        this.balance = balance;
        creationDate = date;
        this.isActive = isActive;
        accountType = type;
    }
    public Set<Transaction> getTransactionSet(){ return transactionSet; }
    public void addTransaction(Transaction transaction) {
        transaction.setAccount(this);
        transactionSet.add(transaction);
    }
    public Client getClient() {return client;}
    public void setClient(Client client) {this.client = client;}
    public Long getId() {
        return id;
    }
    public String getNumber() {return number;}
    public void setNumber(String number) {
        this.number = number;
    }
    public double getBalance() {return balance;}
    public void setBalance(double balance) {this.balance = balance;}
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public boolean isActive() {return isActive;}
    public void setActive(boolean active) {isActive = active;}
    public void setTransactionSet(Set<Transaction> transactionSet) {this.transactionSet = transactionSet;}
    public AccountType getAccountType() {return accountType;}
    public void setAccountType(AccountType accountType) {this.accountType = accountType;}
}