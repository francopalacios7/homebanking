package com.MindHub.HomeBanking;

import com.MindHub.HomeBanking.models.*;
import com.MindHub.HomeBanking.repositories.*;
import com.MindHub.HomeBanking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class HomeBankingApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomeBankingApplication.class, args);
    }
    /*
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository) {
        return args -> {
            Client client1 = new Client ("Franco", "Palacios", "francopalacios7@yahoo.com.ar",passwordEncoder.encode("1234"));
            clientRepository.save(client1);

            Client client2 = new Client("Melba", "Morel", "melba@mindhub.com",passwordEncoder.encode("1234"));
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

            List<Integer> mortgagePayments = List.of(12,24,36,48,60);
            List<Integer> personalPayments = List.of(6,12,24);
            List<Integer> automotivePayments = List.of(6,12,24,36);

            Loan loan1= new Loan("Mortgage",500000.0, mortgagePayments);
            Loan loan2= new Loan("Personal",100000.0, personalPayments);
            Loan loan3= new Loan("Automotive",300000.0, automotivePayments);

            loanRepository.save(loan1);
            loanRepository.save(loan2);
            loanRepository.save(loan3);

            ClientLoan clientLoan1= new ClientLoan(100000.0, 24);
            client1.addClientLoan(clientLoan1);
            loan2.addLoan(clientLoan1);
            clientLoanRepository.save(clientLoan1);

            ClientLoan clientLoan2= new ClientLoan(200000.0, 36);
            client1.addClientLoan(clientLoan2);
            loan3.addLoan(clientLoan2);
            clientLoanRepository.save(clientLoan2);

            ClientLoan clientLoan3= new ClientLoan(400000.0, 60);
            client2.addClientLoan(clientLoan3);
            loan1.addLoan(clientLoan3);
            clientLoanRepository.save(clientLoan3);

            ClientLoan clientLoan4= new ClientLoan(50000.0, 12);
            client2.addClientLoan(clientLoan4);
            loan2.addLoan(clientLoan4);
            clientLoanRepository.save(clientLoan4);

            Card clientCard1 = new Card("MELBA MOREL", CardType.CREDIT, CardColor.GOLD, "1111-2222-3333-4444", 123, LocalDate.now().plusYears(5), LocalDate.now());
            client2.addCard(clientCard1);
            cardRepository.save(clientCard1);

            Card clientCard2 = new Card("MELBA MOREL", CardType.DEBIT, CardColor.TITANIUM, "1234-1234-1234-1234", 124, LocalDate.now().plusYears(5), LocalDate.now());
            client2.addCard(clientCard2);
            cardRepository.save(clientCard2);

            Card clientCard3 = new Card("FRANCO PALACIOS", CardType.DEBIT, CardColor.SILVER, "1345-1894-1674-1294", 393, LocalDate.now().plusYears(5), LocalDate.now());
            client1.addCard(clientCard3);
            cardRepository.save(clientCard3);
        };
    }*/
}
