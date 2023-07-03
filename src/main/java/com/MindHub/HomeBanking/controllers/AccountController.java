package com.MindHub.HomeBanking.controllers;

import com.MindHub.HomeBanking.dtos.AccountDTO;
import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.repositories.AccountRepository;
import com.MindHub.HomeBanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;
    @RequestMapping ("/accounts")
    public List<AccountDTO> getAccounts() {
        return accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());
    }
    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id ){
        return accountRepository.findById(id).map(AccountDTO::new).orElse(null);
    }
    @RequestMapping(path = "/clients/current/accounts", method = RequestMethod.POST)
    public ResponseEntity<Object> createAccount(Authentication authentication) {
        Client client = clientRepository.findByEmail(authentication.getName());
        Set<Account> authentifiedClientAccounts = client.getAccounts();
        String randomNum;
        do {
            Random random = new Random();
            randomNum = "VIN-" + random.nextInt(90000000);
        }while (accountRepository.findByNumber(randomNum) != null);
        if (authentifiedClientAccounts.size() == 3){
            return new ResponseEntity<>("Max amount of accounts reached", HttpStatus.FORBIDDEN);
        }else {
            Account account = new Account(randomNum, 0.0, LocalDate.now());
            client.addAccount(account);
            accountRepository.save(account);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        }
    }
}
