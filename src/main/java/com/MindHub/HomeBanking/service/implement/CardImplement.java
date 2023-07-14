package com.MindHub.HomeBanking.service.implement;

import com.MindHub.HomeBanking.models.Card;
import com.MindHub.HomeBanking.repositories.CardRepository;
import com.MindHub.HomeBanking.repositories.ClientRepository;
import com.MindHub.HomeBanking.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CardImplement implements CardService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CardRepository cardRepository;
    private String randomNum;
    @Override
    public Card findByNumber(String number) {return cardRepository.findByNumber(number);}
    @Override
    public void save(Card card) {cardRepository.save(card);}
}
