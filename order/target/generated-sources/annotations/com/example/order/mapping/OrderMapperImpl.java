package com.example.order.mapping;

import com.example.order.dto.OrderDTO;
import com.example.order.model.OrderModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-05T16:27:13+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDTO toDTO(OrderModel orderModel) {
        if ( orderModel == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderId( orderModel.getOrderId() );
        orderDTO.setClientId( orderModel.getClientId() );
        orderDTO.setProductId( orderModel.getProductId() );
        orderDTO.setQuantity( orderModel.getQuantity() );
        orderDTO.setDateOrder( orderModel.getDateOrder() );

        return orderDTO;
    }

    @Override
    public OrderModel toEntity(OrderDTO dto) {
        if ( dto == null ) {
            return null;
        }

        OrderModel orderModel = new OrderModel();

        orderModel.setOrderId( dto.getOrderId() );
        orderModel.setClientId( dto.getClientId() );
        orderModel.setProductId( dto.getProductId() );
        orderModel.setQuantity( dto.getQuantity() );
        orderModel.setDateOrder( dto.getDateOrder() );

        return orderModel;
    }
}
