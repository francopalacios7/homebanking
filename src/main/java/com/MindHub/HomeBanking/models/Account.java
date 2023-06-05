package com.MindHub.HomeBanking.models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

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

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public Client getOwner() {
        return owner;
    }

    public Account() { }

    public Account(String number, Double balance, LocalDate date, Client client){
        numberAcc = number;
        balanceAcc = balance;
        creationDate = date;
        this.owner = client;

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
