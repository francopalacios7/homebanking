package com.MindHub.HomeBanking.service.implement;

import com.MindHub.HomeBanking.dtos.LoanDTO;
import com.MindHub.HomeBanking.models.*;
import com.MindHub.HomeBanking.repositories.*;
import com.MindHub.HomeBanking.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanImplement implements LoanService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ClientLoanRepository clientLoanRepository;

    @Override
    public List<LoanDTO> getLoans() {
        return loanRepository.findAll().stream().map(LoanDTO::new).collect(Collectors.toList());
    }
    @Override
    public Loan findByName(String name) {
        return loanRepository.findByName(name);
    }
}
