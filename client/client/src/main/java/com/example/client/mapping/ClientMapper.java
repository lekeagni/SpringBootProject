package com.example.client.mapping;

import com.example.client.dto.ClientDTO;
import com.example.client.model.ClientModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO toDTO (ClientModel clientModel);

    ClientModel toEntity (ClientDTO dto);
}
