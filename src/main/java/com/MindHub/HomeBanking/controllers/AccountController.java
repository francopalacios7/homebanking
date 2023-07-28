package com.MindHub.HomeBanking.controllers;

import com.MindHub.HomeBanking.dtos.AccountDTO;
import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.models.AccountType;
import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.service.AccountService;
import com.MindHub.HomeBanking.service.ClientService;
import com.MindHub.HomeBanking.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ClientService clientService;
    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts() {
        return accountService.getAccountsDTO();
    }
    @GetMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id) {
        return accountService.getAccountDTO(id);
    }
    @PostMapping("/clients/current/accounts")
    public ResponseEntity<Object> createAccount(Authentication authentication, @RequestParam AccountType type) {
        Client client = clientService.findByEmail(authentication.getName());
        Set<Account> authentifiedClientAccounts = client.getAccounts().stream().filter(account -> account.isActive() == true).collect(Collectors.toSet());
        String randomNum;
        do {
            randomNum = Utilities.accountNumberGenerator();
        } while (accountService.findByNumber(randomNum) != null);
        if (authentifiedClientAccounts.size() == 3) {
            return new ResponseEntity<>("Max amount of accounts reached", HttpStatus.FORBIDDEN);
        } else{
            Account account = new Account(randomNum, 0.0, LocalDate.now(), true, type);
            client.addAccount(account);
            accountService.save(account);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        }
    }
    @PutMapping("/clients/current/accounts/{id}")
    public ResponseEntity<Object> deleteAccount(Authentication authentication, @PathVariable Long id){
        Client client = clientService.findByEmail(authentication.getName());
        Account accountToDelete = accountService.findById(id);
        accountToDelete.setActive(false);
        accountService.save(accountToDelete);
        if (!client.getAccounts().contains(accountToDelete)){
            return new ResponseEntity<>("The account doesn't belong to this client", HttpStatus.FORBIDDEN);
        }if (accountToDelete.getBalance() != 0){
            return new ResponseEntity<>("The balance must be 0", HttpStatus.FORBIDDEN);
        }else {
            return new ResponseEntity<>("Account eliminated", HttpStatus.ACCEPTED);
        }
    }

}
