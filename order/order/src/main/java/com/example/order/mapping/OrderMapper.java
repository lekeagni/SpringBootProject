package com.example.order.mapping;


import com.example.order.dto.OrderDTO;
import com.example.order.model.OrderModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO toDTO(OrderModel orderModel);

    OrderModel toEntity(OrderDTO dto);
}
