package com.example.client.service.ServiceImpl;

import com.example.client.dto.ClientDTO;
import com.example.client.exception.ClientAlreadyExistException;
import com.example.client.exception.ClientNotFoundException;
import com.example.client.mapping.ClientMapper;
import com.example.client.model.ClientModel;
import com.example.client.repository.ClientRepository;
import com.example.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private  final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ClientDTO createClient(ClientDTO dto) {
        Optional<ClientModel> exist = this.clientRepository.findByEmail(dto.getEmail());
        if(exist.isPresent()){
            throw new ClientAlreadyExistException(dto.getEmail());
        }
        ClientModel clientModel = clientMapper.toEntity(dto);

        return clientMapper.toDTO(this.clientRepository.save(clientModel));
    }

    @Override
    public List<ClientDTO> getAllClient() {
        List<ClientModel> clientModels = this.clientRepository.findAll();
        return clientModels.stream().map(clientMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ClientDTO updateClient(int clientId, ClientDTO dto) {
        ClientModel founds = this.clientRepository.findById(clientId)
                .orElseThrow(()->new ClientNotFoundException(clientId));

            founds.setName(dto.getName());
            founds.setEmail(dto.getEmail());
            founds.setNumber(dto.getNumber());
        ClientModel saved = this.clientRepository.save(founds);
            return  clientMapper.toDTO(saved);

    }

    @Override
    public Boolean deleteClient(int clientId) {
        Optional<ClientModel> found = this.clientRepository.findById(clientId);
        if (found.isPresent()){
            ClientModel cl = found.get();
            this.clientRepository.delete(cl);
            return  true;
        }
         return  false;
    }
}
