package com.MindHub.HomeBanking.controllers;

import com.MindHub.HomeBanking.dtos.TranferDTO;
import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.models.Transaction;
import com.MindHub.HomeBanking.models.TransactionType;
import com.MindHub.HomeBanking.repositories.AccountRepository;
import com.MindHub.HomeBanking.repositories.ClientRepository;
import com.MindHub.HomeBanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<Object> transaction (Authentication authentication, @RequestBody TranferDTO tranferDTO){
        Client client = clientRepository.findByEmail(authentication.getName());
        Set<Account> accounts = client.getAccounts();
        Account accountOrigin = accountRepository.findByNumber(tranferDTO.getNumberOrigin());
        Account accountForeign = accountRepository.findByNumber(tranferDTO.getNumberForeign());
    if (tranferDTO.getDescription().isBlank() || tranferDTO.getAmount() == 0.0 || tranferDTO.getNumberOrigin().isBlank() || tranferDTO.getNumberForeign().isBlank()){
        return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
    }
    if (tranferDTO.getNumberOrigin() == tranferDTO.getNumberForeign()){
        return new ResponseEntity<>("The account numbers are equals", HttpStatus.FORBIDDEN);
    }
    if (accountOrigin == null){
        return new ResponseEntity<>("The origin account doesn't exist", HttpStatus.FORBIDDEN);
    }
    if (accountForeign == null){
        return new ResponseEntity<>("The destination account doesn't exist", HttpStatus.FORBIDDEN);
    }
    if (!accounts.stream().anyMatch(account -> account.getNumber().equals(tranferDTO.getNumberOrigin()))){
        return new ResponseEntity<>("The account doesn't belong to this client", HttpStatus.FORBIDDEN);
    }
    if (accountOrigin.getBalance() < tranferDTO.getAmount()){
        return new ResponseEntity<>("Insufficient funds", HttpStatus.FORBIDDEN);
    }
        Transaction transactionOrigin = new Transaction(TransactionType.DEBIT, -tranferDTO.getAmount(), tranferDTO.getDescription() + " " + tranferDTO.getNumberOrigin(), LocalDateTime.now());
        accountOrigin.addTransaction(transactionOrigin);
        transactionRepository.save(transactionOrigin);
        Transaction transactionForeign = new Transaction(TransactionType.CREDIT, tranferDTO.getAmount(), tranferDTO.getDescription() + " " + tranferDTO.getNumberForeign(), LocalDateTime.now());
        accountForeign.addTransaction(transactionForeign);
        transactionRepository.save(transactionForeign);
        accountRepository.save(accountOrigin);
        accountRepository.save(accountForeign);
        accountOrigin.setBalance(accountOrigin.getBalance()-tranferDTO.getAmount());
        accountForeign.setBalance(accountForeign.getBalance()+tranferDTO.getAmount());
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }
}