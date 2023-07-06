package com.MindHub.HomeBanking.service;

import com.MindHub.HomeBanking.dtos.ClientDTO;
import com.MindHub.HomeBanking.models.Client;
import org.springframework.security.core.Authentication;
import java.util.List;

public interface ClientService {
    Client findByEmail(String email);
    Client findById(Long id);
    List<ClientDTO> getClientsDTO();
    ClientDTO getClientDTO(Long id);
    ClientDTO getAuthenticatedClientDTO(Authentication authentication);
    Client save(Client client);
}
