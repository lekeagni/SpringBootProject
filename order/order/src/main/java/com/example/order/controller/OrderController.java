package com.example.order.controller;

import com.example.order.dto.OrderDTO;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "APIs REST of order", description = "documentation interactive APIs of order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    @Operation(summary = "add order", description = "save new order in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "order created successful"),
//            @ApiResponse(responseCode = "400", description = "this order already exist"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<OrderDTO> create(OrderDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.orderService.createOrder(dto));
    }

    @Operation(summary = "list order", description = "get all orders in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "get all orders successful"),
            @ApiResponse(responseCode = "400", description = "this order already exist"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    @GetMapping()
    public ResponseEntity<List<OrderDTO>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(this.orderService.getAllOrder());
    }

    @PutMapping("/update/{orderId}")
    @Operation(summary = "update order", description = "update order in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "updated order successful"),
            @ApiResponse(responseCode = "400", description = "this order not found"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<OrderDTO>update(@PathVariable int orderId, @RequestBody OrderDTO dto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.orderService.updateOrder(orderId,dto));
    }

    @DeleteMapping("/delete/{orderId}")
    @Operation(summary = "delete order", description = "delete order in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "deleted order successful"),
            @ApiResponse(responseCode = "400", description = "this order already exist"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<Boolean> delete(@PathVariable int orderId){
        boolean exist = this.orderService.delete(orderId);
        if (exist){
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }


}
