package com.MindHub.HomeBanking.controllers;

import com.MindHub.HomeBanking.dtos.CardPaymentDTO;
import com.MindHub.HomeBanking.models.*;
import com.MindHub.HomeBanking.service.AccountService;
import com.MindHub.HomeBanking.service.CardService;
import com.MindHub.HomeBanking.service.ClientService;
import com.MindHub.HomeBanking.service.TransactionService;
import com.MindHub.HomeBanking.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private CardService cardService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;
    private String randomNum;
    private Integer cvv;
    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard(Authentication authentication, @RequestParam CardColor color, @RequestParam CardType type) {
        Client client = clientService.findByEmail(authentication.getName());
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
        Card card = new Card(client.getFirstName() + client.getLastName(), type, color, randomNum, cvv, LocalDate.now().plusYears(5), LocalDate.now(), false);
        client.addCard(card);
        cardService.save(card);
        return new ResponseEntity<>("Card created", HttpStatus.CREATED);
    }
    private boolean clientHasCardOfTypeAndColor(Client client, CardType type, CardColor color) {
        return client.getCardSet()
                .stream()
                .anyMatch(card -> card.getType() == type && card.getColor() == color && card.isDeleted() == false);
    }
    @PutMapping("/clients/current/cards/{id}")
    public ResponseEntity<Object> deleteCard(Authentication authentication, @PathVariable Long id){
        Client client = clientService.findByEmail(authentication.getName());
        Card cardToDelete = cardService.findById(id);
        cardToDelete.setDeleted(true);
        cardService.save(cardToDelete);
        if (client.getCardSet().contains(cardToDelete)){
            return new ResponseEntity<>("Card deleted",HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>("Card not found or not owned by the client", HttpStatus.NOT_FOUND);
        }
    }
    @Transactional
    @CrossOrigin("*")
    @PostMapping("/cards/payment")
    public ResponseEntity<Object> cardPayment(@RequestBody CardPaymentDTO cardPaymentDTO){
        Card card = cardService.findByNumber(cardPaymentDTO.getNumber());
        Client client = card.getClient();
        Set<Account> clientAccounts = client.getAccounts();
        Account account = clientAccounts.stream().filter(acc -> acc.getBalance() > cardPaymentDTO.getAmount()).findFirst().orElse(null);
        if (cardPaymentDTO.getNumber().isBlank() || cardPaymentDTO.getCvv() == null || cardPaymentDTO.getAmount().isNaN() || cardPaymentDTO.getDescription().isBlank()){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (LocalDate.now().isAfter(card.getThruDate())){
            return new ResponseEntity<>("The card is expired", HttpStatus.FORBIDDEN);
        }
        if (account.getBalance() < cardPaymentDTO.getAmount()){
            return new ResponseEntity<>("Insufficient funds", HttpStatus.FORBIDDEN);
        }
        Transaction transaction = new Transaction(TransactionType.DEBIT, cardPaymentDTO.getAmount(), cardPaymentDTO.getDescription() + " " + account.getNumber(), LocalDateTime.now(), account.getBalance() - cardPaymentDTO.getAmount());
        account.setBalance(account.getBalance() - cardPaymentDTO.getAmount());
        accountService.save(account);
        transactionService.save(transaction);
        return new ResponseEntity<>("Transfer successful", HttpStatus.ACCEPTED);
    }
}
