package com.MindHub.HomeBanking.service;

import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.models.ClientLoan;

public interface ClientLoanService {
    ClientLoan findById(Long id);
    ClientLoan save(ClientLoan clientLoan);
}
