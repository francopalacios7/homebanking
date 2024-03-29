package com.MindHub.HomeBanking.models;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ClientLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private Double amount;
    private Integer payments;
    private Integer remainingPayments;
    private Double remainingAmount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loan_id")
    private Loan loan;
    public ClientLoan() {
    }
    public ClientLoan(Double amount, Integer payments) {
        this.amount = amount;
        this.payments = payments;
    }
    public Long getId() {
        return id;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public Integer getPayments() {
        return payments;
    }
    public void setPayments(Integer payments) {
        this.payments = payments;
    }
    @JsonIgnore
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Loan getLoan() {
        return loan;
    }
    public void setLoan(Loan loan) {
        this.loan = loan;
    }
    public Integer getRemainingPayments() {return remainingPayments;}
    public void setRemainingPayments(Integer remainingPayments) {this.remainingPayments = remainingPayments;}
    public Double getRemainingAmount() {return remainingAmount;}
    public void setRemainingAmount(Double remainingAmount) {this.remainingAmount = remainingAmount;}
}