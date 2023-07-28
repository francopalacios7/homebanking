package com.MindHub.HomeBanking.dtos;

import com.MindHub.HomeBanking.models.Account;
import java.util.List;

public class LoanApplicationDTO {
    private Long id;
    private Double amount;
    private String name;
    private Integer payment;
    private String accountNumber;
    public LoanApplicationDTO(){}
    public LoanApplicationDTO(Double amount, Integer payments, String accountNumber, String name) {
        this.amount = amount;
        this.payment = payments;
        this.accountNumber = accountNumber;
        this.name = name;
    }
    public Long getId() {return id;}
    public Double getAmount() {return amount;}
    public Integer getPayment() {return payment;}
    public String getAccountNumber() {return accountNumber;}
    public String getName() {return name;}
}
