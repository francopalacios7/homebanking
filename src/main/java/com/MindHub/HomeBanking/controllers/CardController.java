package com.MindHub.HomeBanking.controllers;

import com.MindHub.HomeBanking.models.Card;
import com.MindHub.HomeBanking.models.CardColor;
import com.MindHub.HomeBanking.models.CardType;
import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.repositories.CardRepository;
import com.MindHub.HomeBanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CardRepository cardRepository;
    private String randomNum;
    private Integer cvv;
    @RequestMapping(path = "/clients/current/cards", method = RequestMethod.POST)
    public ResponseEntity<Object> createCard(Authentication authentication, @RequestParam CardColor color, @RequestParam CardType type) {
        String email = authentication.getName();
        Client client = clientRepository.findByEmail(email);
        Random random = new Random();
        if (client == null) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        if (color == null || type == null) {
            return new ResponseEntity<>("Missing data", HttpStatus.BAD_REQUEST);
        }
        if (clientHasCardOfTypeAndColor(client, type, color)) {
            return new ResponseEntity<>("A card of the specified type and color already exists", HttpStatus.FORBIDDEN);
        }
        do {
            Random random1 = new Random();
            randomNum = random1.nextInt(9999) + "-" + random1.nextInt(9999) + "-" + random1.nextInt(9999) + "-" + random1.nextInt(9999);
        } while (cardRepository.findByNumber(randomNum) != null);
            cvv = random.nextInt(999);
        Card card = new Card(client.getFirstName() + client.getLastName(), type, color, randomNum, cvv, LocalDate.now().plusYears(5), LocalDate.now());
        client.addCard(card);
        cardRepository.save(card);
        return new ResponseEntity<>("Card created", HttpStatus.CREATED);
    }
    private boolean clientHasCardOfTypeAndColor(Client client, CardType type, CardColor color) {
        return client.getCardSet()
                .stream()
                .anyMatch(card -> card.getType() == type && card.getColor() == color);
    }
}
