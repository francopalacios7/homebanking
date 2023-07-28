package com.MindHub.HomeBanking.service.implement;

import com.MindHub.HomeBanking.dtos.ClientDTO;
import com.MindHub.HomeBanking.models.Client;
import com.MindHub.HomeBanking.repositories.ClientRepository;
import com.MindHub.HomeBanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientImplement implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Override
    public Client findByEmail(String email) {
      return clientRepository.findByEmail(email);
    }
    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }
    @Override
    public List<ClientDTO> getClientsDTO() {return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());}
    @Override
    public ClientDTO getClientDTO(Long id) {return clientRepository.findById(id)
            .map(ClientDTO::new)
            .orElse(null);}
    @Override
    public ClientDTO getAuthenticatedClientDTO(Authentication authentication) {return new ClientDTO(clientRepository.findByEmail(authentication.getName()));}
    @Override
    public Client save(Client client) {
       return clientRepository.save(client);
    }
}
