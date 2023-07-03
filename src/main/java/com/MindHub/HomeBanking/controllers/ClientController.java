package com.MindHub.HomeBanking.controllers;

import com.MindHub.HomeBanking.dtos.ClientDTO;
import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.repositories.AccountRepository;
import com.MindHub.HomeBanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping ("/api")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private String randomNum;
    @RequestMapping ("/clients")
    public List<ClientDTO> getClients() {
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
    }
    @RequestMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id ){
       return clientRepository.findById(id)
               .map(ClientDTO::new)
               .orElse(null);
    }
    @RequestMapping(path = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password) {
        if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (clientRepository.findByEmail(email) !=  null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }
        else{
            Client client = clientRepository.save(new Client(firstName, lastName, email, passwordEncoder.encode(password)));
            do{
                Random random = new Random();
                randomNum = "VIN-" + random.nextInt(99999999);
            }while (accountRepository.findByNumber(randomNum) != null);
            Account account = new Account(randomNum, 0.0, LocalDate.now());
            client.addAccount(account);
            accountRepository.save(account);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    @RequestMapping("/clients/current")
    public ClientDTO getAuthenticatedClient(Authentication authentication){
        return new ClientDTO(clientRepository.findByEmail(authentication.getName()));
    }
}