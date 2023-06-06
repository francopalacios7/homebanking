package com.MindHub.HomeBanking.repositories;

import com.MindHub.HomeBanking.models.Account;
import com.MindHub.HomeBanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findById(long id);

}
