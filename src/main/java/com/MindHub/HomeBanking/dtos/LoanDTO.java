package com.MindHub.HomeBanking.dtos;

import com.MindHub.HomeBanking.models.Loan;
import java.util.List;

public class LoanDTO {
    private Long id;
    private String name;
    private List<Integer> payments;
    private Double maxAmount;
    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.payments = loan.getPayments();
        this.maxAmount = loan.getMaxAmount();
    }
    public Long getId() {return id;}
    public String getName() {return name;}
    public List<Integer> getPayments() {return payments;}
    public Double getMaxAmount() {return maxAmount;}
}
