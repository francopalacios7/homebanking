package com.MindHub.HomeBanking.service.implement;

import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.models.ClientLoan;
import com.MindHub.HomeBanking.repositories.ClientLoanRepository;
import com.MindHub.HomeBanking.service.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientLoanImplement implements ClientLoanService {
    @Autowired
    ClientLoanRepository clientLoanRepository;
    @Override
    public ClientLoan findById(Long id) {return clientLoanRepository.findById(id).orElse(null);}
    @Override
    public ClientLoan save(ClientLoan clientLoan) {return clientLoanRepository.save(clientLoan);}

}
