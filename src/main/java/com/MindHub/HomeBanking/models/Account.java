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

    private long id;
    private String numberAcc;
    private double balanceAcc;
    private LocalDate creationDate;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="owner_id")
    private Client owner;

    @OneToMany(mappedBy="account", fetch=FetchType.EAGER)
    Set<Transaction> transactionSet = new HashSet<>();
    public Set<Transaction> getTransactionSet(){ return transactionSet; }
    public void addTransaction(Transaction transaction) {
        transaction.setAccount(this);
        transactionSet.add(transaction);
    }
    public void setOwner(Client owner) {
        this.owner = owner;
    }
    @JsonIgnore
    public Client getOwner() {
        return owner;
    }

    public Account() { }

    public Account(String number, Double balance, LocalDate date){
        numberAcc = number;
        balanceAcc = balance;
        creationDate = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;

    }

    public String getNumber() {
        return numberAcc;

    }

    public void setNumber(String number) {
        this.numberAcc = number;

    }

    public double getBalance() {
        return balanceAcc;

    }

    public void setBalance(double balance) {
        this.balanceAcc = balance;

    }

    public LocalDate getCreationDate() {
        return creationDate;

    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;

    }


}
