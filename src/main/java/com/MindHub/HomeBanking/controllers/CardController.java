package com.MindHub.HomeBanking.controllers;

import com.MindHub.HomeBanking.models.Card;
import com.MindHub.HomeBanking.models.CardColor;
import com.MindHub.HomeBanking.models.CardType;
import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.service.CardService;
import com.MindHub.HomeBanking.service.ClientService;
import com.MindHub.HomeBanking.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private CardService cardService;
    @Autowired
    private ClientService clientService;
    private String randomNum;
    private Integer cvv;
    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard(Authentication authentication, @RequestParam CardColor color, @RequestParam CardType type) {
        String email = authentication.getName();
        Client client = clientService.findByEmail(email);
        
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
            randomNum = Utilities.cardNumberGenerator();
        } while (cardService.findByNumber(randomNum) != null);
        cvv = Utilities.cvvGenerator();
        Card card = new Card(client.getFirstName() + client.getLastName(), type, color, randomNum, cvv, LocalDate.now().plusYears(5), LocalDate.now());
        client.addCard(card);
        cardService.save(card);
        return new ResponseEntity<>("Card created", HttpStatus.CREATED);
    }
    private boolean clientHasCardOfTypeAndColor(Client client, CardType type, CardColor color) {
        return client.getCardSet()
                .stream()
                .anyMatch(card -> card.getType() == type && card.getColor() == color);
    }
}
