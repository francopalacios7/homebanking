package com.MindHub.HomeBanking.service;

import com.MindHub.HomeBanking.models.Card;

public interface CardService {
Card findByNumber(String number);
Card findById(Long id);
void save(Card card);
}
