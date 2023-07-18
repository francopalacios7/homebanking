package com.MindHub.HomeBanking.controllers;

import com.MindHub.HomeBanking.dtos.LoanApplicationDTO;
import com.MindHub.HomeBanking.dtos.LoanDTO;
import com.MindHub.HomeBanking.models.*;
import com.MindHub.HomeBanking.repositories.ClientLoanRepository;
import com.MindHub.HomeBanking.service.AccountService;
import com.MindHub.HomeBanking.service.ClientService;
import com.MindHub.HomeBanking.service.LoanService;
import com.MindHub.HomeBanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class LoanController {
    @Autowired
    LoanService loanService;
    @Autowired
    ClientService clientService;
    @Autowired
    AccountService accountService;
    @Autowired
    ClientLoanRepository clientLoanRepository;
    @Autowired
    TransactionService transactionService;
    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<Object> loan(Authentication authentication, @RequestBody LoanApplicationDTO loanApplicationDTO){
        Client client = clientService.findByEmail(authentication.getName());
        Set<Account> accounts = client.getAccounts();
        Loan loan = loanService.findByName(loanApplicationDTO.getName());
        Account accountDestination = accountService.findByNumber(loanApplicationDTO.getAccountNumber());
        Double loanAmount = null;
        if (loanApplicationDTO.getAmount() == 0 || loanApplicationDTO.getPayment() == 0 || loanApplicationDTO.getAccountNumber().isBlank() || loanApplicationDTO.getName().isBlank()){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (loan == null){
            return new ResponseEntity<>("This loan doesn't exist", HttpStatus.FORBIDDEN);
        }
        if (loanApplicationDTO.getAmount() > loan.getMaxAmount()){
            return new ResponseEntity<>("The amount requested is exceeding the maximum amount of this loan", HttpStatus.FORBIDDEN);
        }
        if (!loan.getPayments().contains(loanApplicationDTO.getPayment())){
            return new ResponseEntity<>("The payments requested aren't available for this loan", HttpStatus.FORBIDDEN);
        }
        if (accountDestination == null){
            return new ResponseEntity<>("The account doesn't exist, please check the numbers", HttpStatus.FORBIDDEN);
        }
        if (!accounts.stream().anyMatch(account -> account.getNumber().equals(loanApplicationDTO.getAccountNumber()))){
            return new ResponseEntity<>("The account doesn't belong to this client", HttpStatus.FORBIDDEN);
        }
        if (loanApplicationDTO.getName().equals("Mortgage")){
            loanAmount = loanApplicationDTO.getAmount() + loanApplicationDTO.getAmount() * 0.1;
        }
        if (loanApplicationDTO.getName().equals("Personal")){
            loanAmount = loanApplicationDTO.getAmount() + loanApplicationDTO.getAmount() * 0.25;
        }
        if (loanApplicationDTO.getName().equals("Automotive")){
            loanAmount = loanApplicationDTO.getAmount() + loanApplicationDTO.getAmount() * 0.2;
        }
        ClientLoan clientLoan = new ClientLoan(loanAmount, loanApplicationDTO.getPayment());
        Double newBalance = accountDestination.getBalance()+loanApplicationDTO.getAmount();
        Transaction loanTransaction = new Transaction(TransactionType.CREDIT, loanApplicationDTO.getAmount(), loanApplicationDTO.getName() + " " + "loan approved", LocalDateTime.now(), newBalance);
        accountDestination.setBalance(accountDestination.getBalance() + loanApplicationDTO.getAmount());
        client.addClientLoan(clientLoan);
        loan.addLoan(clientLoan);
        accountDestination.addTransaction(loanTransaction);
        accountService.save(accountDestination);
        transactionService.save(loanTransaction);
        clientLoanRepository.save(clientLoan);
        return new ResponseEntity<>("Loan successfully assigned",HttpStatus.CREATED);
    }
    @PostMapping("/admin/loans")
    public ResponseEntity<Object> createLoan(Authentication authentication, @RequestBody LoanDTO loanDTO){
        Client admin = clientService.findByEmail(authentication.getName());
        if (loanDTO.getMaxAmount() == 0 || loanDTO.getPayments().isEmpty() || loanDTO.getName().isBlank()){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        Loan loan = new Loan(loanDTO.getName(), loanDTO.getMaxAmount(), loanDTO.getPayments());
        loanService.save(loan);
        return new ResponseEntity<>("Loan successfully created",HttpStatus.CREATED);
    }
    @GetMapping("/loans")
    public List<LoanDTO> getLoans() {
        return loanService.getLoans();
    }
}
