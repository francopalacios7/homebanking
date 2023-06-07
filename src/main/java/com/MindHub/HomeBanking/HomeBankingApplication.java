package com.MindHub.HomeBanking;

import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.repositories.AccountRepository;
import com.MindHub.HomeBanking.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class HomeBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeBankingApplication.class, args);

    }

    @Bean
    public CommandLineRunner innitData(ClientRepository clientRepository, AccountRepository accountRepository) {
        return args -> {
            Client client1 = new Client ("Franco", "Palacios", "francopalacios7@yahoo.com.ar");
            clientRepository.save(client1);

            Client client2 = new Client("Melba", "Morel", "melba@mindhub.com");
            clientRepository.save(client2);

            Account account1 = new Account("VIN001",  5000.0, LocalDate.now(), client2);
            accountRepository.save(account1);

            Account account2 = new Account("VIN002",  7500.0, LocalDate.now().plusDays(1), client2);
            accountRepository.save(account2);

            Account account3 = new Account("VIN003", 10000.0, LocalDate.now().minusDays(1), client1);
            accountRepository.save(account3);
        };
    }
}
