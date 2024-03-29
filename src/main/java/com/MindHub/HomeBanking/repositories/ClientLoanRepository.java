package com.MindHub.HomeBanking.repositories;

import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.models.ClientLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientLoanRepository extends JpaRepository<ClientLoan, Long> {
}
