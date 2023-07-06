package com.MindHub.HomeBanking.service.implement;

import com.MindHub.HomeBanking.dtos.AccountDTO;
import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.repositories.AccountRepository;
import com.MindHub.HomeBanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountImplement implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    private String randomNum;
    @Override
    public Account findByNumber(String number) {return accountRepository.findByNumber(randomNum);}
    @Override
    public List<AccountDTO> getAccountsDTO() {return accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());}
    @Override
    public AccountDTO getAccountDTO(Long id) {return accountRepository.findById(id).map(AccountDTO::new).orElse(null);}
    @Override
    public Account save(Account account) {return accountRepository.save(account);}
}

