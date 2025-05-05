package com.example.order.service;

import com.example.order.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    public OrderDTO createOrder(OrderDTO dto);

    public List<OrderDTO>getAllOrder();

    public OrderDTO updateOrder(int orderId, OrderDTO dto);

    public Boolean delete(int orderId);
}
