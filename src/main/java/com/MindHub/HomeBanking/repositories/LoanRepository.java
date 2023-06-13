package com.MindHub.HomeBanking.repositories;

import com.MindHub.HomeBanking.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.Entity;

@RepositoryRestResource
public interface LoanRepository extends JpaRepository<Loan, Long> {

}
