package com.MindHub.HomeBanking.dtos;

import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.models.Client;

import java.time.LocalDate;

public class AccountDTO {

    private long id;
    private String numberAcc;
    private double balanceAcc;
    private LocalDate creationDate;

    public AccountDTO(){}

    public AccountDTO(Account account){
        this.id = account.getId();
        this.numberAcc = account.getNumber();
        this.balanceAcc = account.getBalance();
        this.creationDate = account.getCreationDate();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumberAcc() {
        return numberAcc;
    }

    public void setNumberAcc(String numberAcc) {
        this.numberAcc = numberAcc;
    }

    public double getBalanceAcc() {
        return balanceAcc;
    }

    public void setBalanceAcc(double balanceAcc) {
        this.balanceAcc = balanceAcc;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
