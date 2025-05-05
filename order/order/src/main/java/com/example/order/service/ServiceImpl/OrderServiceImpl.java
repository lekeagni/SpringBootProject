package com.example.order.service.ServiceImpl;

import com.example.order.dto.ClientDTO;
import com.example.order.dto.OrderDTO;
import com.example.order.dto.ProductDTO;
import com.example.order.exception.ProductNotFoundException;
import com.example.order.exception.ClientNotFoundException;
import com.example.order.exception.OrderNotFoundException;
import com.example.order.mapping.OrderMapper;
import com.example.order.model.OrderModel;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    public OrderServiceImpl(OrderRepository orderRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderDTO createOrder(OrderDTO dto) {

//   check if product exist
        ProductDTO productDTO;
        try {
            restTemplate.getForObject("http://PRODUCT/products/"+dto.getProductId(), ProductDTO.class);
        }catch (HttpClientErrorException.NotFound e){
            throw  new ProductNotFoundException(dto.getProductId());
        }

//        check if customer exist
        ClientDTO clientDTO;
        try {
            restTemplate.getForObject("http://CLIENT/clients/"+dto.getClientId(), ClientDTO.class);
        }catch (HttpClientErrorException.NotFound e){
            throw new ClientNotFoundException(dto.getClientId());
        }

        OrderModel orderModel = new OrderModel();
        orderModel.setClientId(dto.getClientId());
        orderModel.setProductId(dto.getProductId());
        orderModel.setQuantity(dto.getQuantity());
        orderModel.setDateOrder(LocalDateTime.now());

        return orderMapper.toDTO(this.orderRepository.save(orderModel));
    }

    @Override
    public List<OrderDTO> getAllOrder() {
        List<OrderModel> exist = this.orderRepository.findAll();
        return exist.stream().map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO updateOrder(int orderId, OrderDTO dto) {
        return null;
    }

    @Override
    public Boolean delete(int orderId) {
        Optional<OrderModel> found = this.orderRepository.findById(orderId);
        if (found.isPresent()){
            OrderModel or = found.get();
            this.orderRepository.delete(or);
            return true;
        }
        throw new OrderNotFoundException(orderId);
    }
}
