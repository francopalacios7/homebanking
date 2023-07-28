package com.MindHub.HomeBanking.service;

import com.MindHub.HomeBanking.dtos.AccountDTO;
import com.MindHub.HomeBanking.models.Account;
import java.util.List;

public interface AccountService {
    Account findByNumber(String number);
    List<AccountDTO> getAccountsDTO();
    AccountDTO getAccountDTO(Long id);
    Account save(Account account);
    Account findById(Long id);
}
