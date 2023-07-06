package com.MindHub.HomeBanking.controllers;

import com.MindHub.HomeBanking.dtos.AccountDTO;
import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.service.AccountService;
import com.MindHub.HomeBanking.service.ClientService;
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

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    AccountService accountService;

    @Autowired
    private ClientService clientService;

    @RequestMapping("/accounts")
    public List<AccountDTO> getAccounts() {
        return accountService.getAccountsDTO();
    }

    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id) {
        return accountService.getAccountDTO(id);
    }

    @RequestMapping(path = "/clients/current/accounts", method = RequestMethod.POST)
    public ResponseEntity<Object> createAccount(Authentication authentication) {
        Client client = clientService.findByEmail(authentication.getName());
        Set<Account> authentifiedClientAccounts = client.getAccounts();
        String randomNum;
        do {
            Random random = new Random();
            randomNum = "VIN-" + random.nextInt(90000000);
        } while (accountService.findByNumber(randomNum) != null);
        if (authentifiedClientAccounts.size() == 3) {
            return new ResponseEntity<>("Max amount of accounts reached", HttpStatus.FORBIDDEN);
        } else {
            Account account = new Account(randomNum, 0.0, LocalDate.now());
            client.addAccount(account);
            accountService.save(account);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        }
    }
}
