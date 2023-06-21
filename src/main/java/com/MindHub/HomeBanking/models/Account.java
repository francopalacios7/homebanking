package com.MindHub.HomeBanking.models;
import net.minidev.json.annotate.JsonIgnore;
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
    private Double balance;
    private LocalDate creationDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;
    @OneToMany(mappedBy="account", fetch=FetchType.EAGER)
    Set<Transaction> transactionSet = new HashSet<>();
    public Account() { }
    public Account(String number, Double balance, LocalDate date){
        this.number = number;
        this.balance = balance;
        creationDate = date;
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
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
