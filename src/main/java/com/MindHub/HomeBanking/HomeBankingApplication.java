package com.MindHub.HomeBanking;

import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomeBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeBankingApplication.class, args);

    }

    @Bean
    public CommandLineRunner innitData(ClientRepository clientRepository) {
        return args -> {
            Client client1 = new Client ("Franco", "Palacios", "francopalacios7@yahoo.com.ar");
            clientRepository.save(client1);

            Client client2 = new Client("Melba", "Morel", "melba@mindhub.com");
            clientRepository.save(client2);
        };
    }


}
