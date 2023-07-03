package com.MindHub.HomeBanking.dtos;

public class TranferDTO {
    private String numberOrigin;
    private String numberForeign;
    private double amount;
    private String description;
    public TranferDTO(){
    }
    public TranferDTO(String numberOrigin, String numberForeign, double amount, String description) {
        this.numberOrigin = numberOrigin;
        this.numberForeign = numberForeign;
        this.amount = amount;
        this.description = description;
    }
    public String getNumberOrigin() {return numberOrigin;}
    public String getNumberForeign() {return numberForeign;}
    public double getAmount() {return amount;}
    public String getDescription() {return description;}
}
