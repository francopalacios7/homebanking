package com.MindHub.HomeBanking;

import com.MindHub.HomeBanking.models.*;
import com.MindHub.HomeBanking.repositories.AccountRepository;
import com.MindHub.HomeBanking.repositories.ClientRepository;
import com.MindHub.HomeBanking.repositories.LoanRepository;
import com.MindHub.HomeBanking.repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class HomeBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeBankingApplication.class, args);

    }

    @Bean
    public CommandLineRunner innitData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository) {
        return args -> {
            Client client1 = new Client ("Franco", "Palacios", "francopalacios7@yahoo.com.ar");
            clientRepository.save(client1);

            Client client2 = new Client("Melba", "Morel", "melba@mindhub.com");
            clientRepository.save(client2);

            Account account1 = new Account("VIN001",  5000.0, LocalDate.now());
            client2.addAccount(account1);
            accountRepository.save(account1);

            Account account2 = new Account("VIN002",  7500.0, LocalDate.now().plusDays(1));
            client2.addAccount(account2);
            accountRepository.save(account2);

            Account account3 = new Account("VIN003", 10000.0, LocalDate.now().minusDays(1));
            client1.addAccount(account3);
            accountRepository.save(account3);

            Transaction transaction1 = new Transaction(TransactionType.DEBIT, -4500.0, "hola", LocalDateTime.now());
            account2.addTransaction(transaction1);
            transactionRepository.save(transaction1);

            Transaction transaction2= new Transaction(TransactionType.DEBIT, -4500000.0, "hola", LocalDateTime.now());
            account2.addTransaction(transaction2);
            transactionRepository.save(transaction2);

            Transaction transaction3= new Transaction(TransactionType.CREDIT, 4500.0, "hola", LocalDateTime.now());
            account2.addTransaction(transaction3);
            transactionRepository.save(transaction3);

            Set<Integer> mortgagePayments = Set.of(12,24,36,48,60);
            Set<Integer> personalPayments = Set.of(6,12,24);
            Set<Integer> automotivePayments = Set.of(6,12,24,36);


            Loan loan1= new Loan("Mortgage",500000.0, mortgagePayments);
            Loan loan2= new Loan("Personal",100000.0, personalPayments);
            Loan loan3= new Loan("Automotive",300000.0, automotivePayments);

            loanRepository.save(loan1);
            loanRepository.save(loan2);
            loanRepository.save(loan3);


        };
    }
}
