package com.MindHub.HomeBanking.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.MindHub.HomeBanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {
        List<Client> findById(long id);
    }
