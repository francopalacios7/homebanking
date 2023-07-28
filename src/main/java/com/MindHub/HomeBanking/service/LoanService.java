package com.MindHub.HomeBanking.service;

import com.MindHub.HomeBanking.dtos.LoanApplicationDTO;
import com.MindHub.HomeBanking.dtos.LoanDTO;
import com.MindHub.HomeBanking.models.Loan;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import java.util.List;

@RepositoryRestResource
public interface LoanService {
    List<LoanDTO> getLoans();
    Loan findByName(String name);
    Loan save(Loan loan);
}
