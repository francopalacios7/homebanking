package com.MindHub.HomeBanking.dtos;

import com.MindHub.HomeBanking.models.ClientLoan;

public class ClientLoanDTO {
    private Long id;
    private Long loanId;
    private String name;
    private Double amount;
    private Integer payments;
    private Double remainingAmount;
    private Integer remainingPayments;

    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id = clientLoan.getId();
        this.loanId = clientLoan.getLoan().getId();
        this.name = clientLoan.getLoan().getName();
        this.amount = clientLoan.getAmount();
        this.payments = clientLoan.getPayments();
    }
    public Long getId() {
        return id;
    }
    public Long getLoanId() {
        return loanId;
    }
    public String getName() {
        return name;
    }
    public Double getAmount() {
        return amount;
    }
    public Integer getPayments() {
        return payments;
    }
    public Double getRemainingAmount() {return remainingAmount;}
    public Integer getRemainingPayments() {return remainingPayments;}
}
