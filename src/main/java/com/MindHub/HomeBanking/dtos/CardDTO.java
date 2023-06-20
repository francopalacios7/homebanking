package com.MindHub.HomeBanking.dtos;

import com.MindHub.HomeBanking.models.Card;
import com.MindHub.HomeBanking.models.CardColor;
import com.MindHub.HomeBanking.models.CardType;
import java.time.LocalDate;

public class CardDTO {
    private Long id;
    private String cardHolder;
    private CardType type;
    private CardColor color;
    private String number;
    private Integer cvv;
    private LocalDate thruDate;
    private LocalDate fromDate;
    public CardDTO(Card card) {
        this.id = card.getId();
        this.cardHolder = card.getCardHolder();
        this.type = card.getType();
        this.color = card.getColor();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.thruDate = card.getThruDate();
        this.fromDate = card.getFromDate();
    }
    public Long getId() {
        return id;
    }
    public String getCardHolder() {
        return cardHolder;
    }
    public CardType getType() {
        return type;
    }
    public CardColor getColor() {
        return color;
    }
    public String getNumber() {
        return number;
    }
    public Integer getCvv() {
        return cvv;
    }
    public LocalDate getThruDate() {
        return thruDate;
    }
    public LocalDate getFromDate() {
        return fromDate;
    }
}
