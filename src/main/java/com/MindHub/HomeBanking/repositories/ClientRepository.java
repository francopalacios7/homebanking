package com.MindHub.HomeBanking.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.MindHub.HomeBanking.models.Client;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {
       Client findByEmail(String email);
    }
