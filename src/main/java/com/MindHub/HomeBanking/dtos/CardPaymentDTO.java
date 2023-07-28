package com.MindHub.HomeBanking.dtos;

import javax.persistence.criteria.CriteriaBuilder;

public class CardPaymentDTO {
    private String number;
    private Integer cvv;
    private Double amount;
    private String description;
    public CardPaymentDTO() {
    }
    public CardPaymentDTO(String number, Integer cvv, Double amount, String description) {
        this.number = number;
        this.cvv = cvv;
        this.amount = amount;
        this.description = description;
    }
    public String getNumber() {return number;}
    public Integer getCvv() {return cvv;}
    public Double getAmount() {return amount;}
    public String getDescription() {return description;}
}
