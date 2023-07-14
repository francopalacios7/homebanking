package com.MindHub.HomeBanking;

import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.repositories.AccountRepository;
import com.MindHub.HomeBanking.utils.Utilities;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class AccountTest {
    @Autowired
    AccountRepository accountRepository;
    @Test
    public void existAccounts(){
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts,is(not(empty())));
    }
    @Test
    public void hasVIN(){
        String account = Utilities.accountNumberGenerator();
        assertThat(account, hasLength(12));
    }
}
