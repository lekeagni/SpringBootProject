package com.example.client.mapping;

import com.example.client.dto.ClientDTO;
import com.example.client.model.ClientModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-03T11:45:36+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientDTO toDTO(ClientModel clientModel) {
        if ( clientModel == null ) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setClientId( clientModel.getClientId() );
        clientDTO.setName( clientModel.getName() );
        clientDTO.setEmail( clientModel.getEmail() );
        clientDTO.setNumber( clientModel.getNumber() );

        return clientDTO;
    }

    @Override
    public ClientModel toEntity(ClientDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ClientModel clientModel = new ClientModel();

        clientModel.setClientId( dto.getClientId() );
        clientModel.setName( dto.getName() );
        clientModel.setEmail( dto.getEmail() );
        clientModel.setNumber( dto.getNumber() );

        return clientModel;
    }
}
