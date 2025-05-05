package com.example.client.service;

import com.example.client.dto.ClientDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    public ClientDTO createClient(ClientDTO dto);

    public List<ClientDTO> getAllClient();

    public ClientDTO getClientById(int clientId);

    public ClientDTO updateClient(int clientId, ClientDTO dto);

    public Boolean deleteClient(int clientId);
}
